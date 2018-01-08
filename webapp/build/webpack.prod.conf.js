'use strict'
const path = require('path')
const utils = require('./utils')
const webpack = require('webpack')
const config = require('../config')
const merge = require('webpack-merge')
const baseWebpackConfig = require('./webpack.base.conf')
const CopyWebpackPlugin = require('copy-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')
const OptimizeCSSPlugin = require('optimize-css-assets-webpack-plugin')
const UglifyJsPlugin = require('uglifyjs-webpack-plugin')

const env = require('../config/prod.env')

const webpackConfig = merge(baseWebpackConfig, {
    module: {
        rules: utils.styleLoaders({
            sourceMap: config.build.productionSourceMap,
            extract: true,
            usePostCSS: true
        })
    },
    devtool: config.build.productionSourceMap ? config.build.devtool : false,
    output: {
        path: config.build.assetsRoot,
        filename: utils.assetsPath('js/[name].[chunkhash].js'),
        chunkFilename: utils.assetsPath('js/[id].[chunkhash].js')
    },
    plugins: [
        /*
         * webpack.DefinePlugin用来指定生产环境
         *  允许你创建一个在编译时可以配置的全局常量
         *  可以用以区分代码是在开发环境还是生产环境触发
         */
        new webpack.DefinePlugin({
            'process.env': env
        }),
        // 代码压缩
        new UglifyJsPlugin({
            uglifyOptions: {
                compress: {
                    warnings: false
                }
            },
            sourceMap: config.build.productionSourceMap, // 使用sourceMap将错误消息位置映射到模块
            parallel: true // 使用多进程并行运行来提高构建速度
        }),
        /*
         * 提取css片段到单独css文件
         */
        new ExtractTextPlugin({
            filename: utils.assetsPath('css/[name].[contenthash].css'),
            /*
             * 从所有其他块中提取（默认情况下，它只从最初的块中提取）
             * 当使用CommonsChunkPlugin并且公共块中有ExtractTextPlugin.extract块时，allChunks必须设置为true
             *  当其设置为false时，将不会从代码拆分块中提取CSS。
             *  当代码拆分块被webpack加载时，他们的CSS将会被style-loader动态插入。
             */
            allChunks: true,
        }),
        /*
         * 它将在Webpack构建期间搜索CSS资源，并优化\最小化CSS（默认情况下，它使用cssnano，但可以指定一个自定义的CSS处理器）。
         *  压缩提取的CSS。 我们使用这个插件，以便可以从不同组件中重复使用CSS。
         */
        new OptimizeCSSPlugin({
            cssProcessorOptions: config.build.productionSourceMap
                ? {safe: true, map: {inline: false}}
                : {safe: true}
        }),
        /*
         * 基本作用就是生成html文件
         *  原理：将 webpack中`entry`配置的相关入口thunk和`extract-text-webpack-plugin`抽取的css样式，插入到该插件提供的`template`或者`templateContent`配置项指定的内容基础上生成一个html文件，具体插入方式是将样式`link`插入到`head`元素中，`script`插入到`head`或者`body`中。
         *  主要作用：
         *      1. 为html文件中引入的外部资源如script、link动态添加每次compile后的hash，防止引用缓存的外部文件问题
         *      2. 可以生成创建html入口文件，比如单页面可以生成一个html文件入口，配置N个html-webpack-plugin可以生成N个页面入口
         */
        new HtmlWebpackPlugin({
            /*
             * 1.filename配置的html文件目录是相对于webpackConfig.output.path路径而言的，不是相对于当前项目目录结构的。
             * 2.指定生成的html文件内容中的link和script路径是相对于生成目录下的，写路径的时候请写生成目录下的相对路径。
             */
            filename: config.build.index,
            /*
             * 本地模板文件的位置
             *  1.template配置项在html文件使用file-loader时，其所指定的位置找不到，导致生成的html文件内容不是期望的内容。
             *  2.为template指定的模板文件没有指定任何loader的话，默认使用ejs-loader。
             */
            template: 'index.html',
            /*
             * 向template或者templateContent中注入所有静态资源，不同的配置值注入的位置不经相同。
             *  1、true或者body：所有JavaScript资源插入到body元素的底部
             *  2、head: 所有JavaScript资源插入到head元素中
             *  3、false： 所有静态资源css和JavaScript都不会注入到模板文件中
             */
            inject: true,
            hash: true, // 是否为所有注入的静态资源添加webpack每次编译产生的唯一hash值 xxx.js?hash
            minify: {
                removeComments: true, // 删除注释
                collapseWhitespace: true, // 去除空格
                removeAttributeQuotes: true, // 去除属性引用
                caseSensitive: true, // 大小写敏感
                removeEmptyAttributes: true, // 去除空属性
            },
            // 必须通过CommonsChunkPlugin一致地处理多个块
            chunksSortMode: 'dependency'
        }),
        /*
         * 生成稳定的模块 ID，避免频繁的 chunk 内容变动（持久化缓存）
         */
        new webpack.HashedModuleIdsPlugin(),
        /*
         * wp2处理后的每个模块均生成一个函数，降低浏览器中JS执行效率，这主要是闭包函数降低了JS引擎解析速度
         * wp3将一些有联系的模块，放到一个闭包函数里面去，通过减少闭包函数数量从而加快JS的执行速度
         */
        new webpack.optimize.ModuleConcatenationPlugin(),
        /*
         * 建立一个独立文件(又称作 chunk)的功能，这个文件包括多个入口 chunk 的公共模块
         * 通过将公共模块拆出来，最终合成的文件能够在最开始的时候加载一次，便存起来到缓存中供后续使用
         */
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor', // 提取公共代码块后js文件的名字
            minChunks (module) {
                // node_modules中的所有必需模块都被提取到vendor
                return (
                    module.resource &&
                    /\.js$/.test(module.resource) &&
                    module.resource.indexOf(path.join(__dirname, '../node_modules')) === 0
                )
            }
        }),
        // 将webpack运行时和模块清单解压到自己的文件中
        // 防止更新应用程序包时更新vendor hash
        new webpack.optimize.CommonsChunkPlugin({
            name: 'manifest',
            minChunks: Infinity // 会生成公共chunk，但里面没有模块
        }),
        // 这个实例从代码拆分块中提取共享块并捆绑它们
        new webpack.optimize.CommonsChunkPlugin({
            name: 'app',
            async: 'vendor-async',
            children: true, // 公共chunk的子模块都会被选择
            /*
             * 在传入  公共chunk(commons chunk) 之前所需要包含的最少数量的 chunks 。
             * 数量必须大于等于2，或者少于等于 chunks的数量
             * 传入 `Infinity` 会马上生成 公共chunk，但里面没有模块。
             */
            minChunks: 3
        }),

        // 拷贝通用静态资源
        new CopyWebpackPlugin([
            {
                from: path.resolve(__dirname, '../static'),
                to: config.build.assetsSubDirectory,
                ignore: ['.*']
            }
        ])
    ]
})

if (config.build.productionGzip) {
    const CompressionWebpackPlugin = require('compression-webpack-plugin')

    webpackConfig.plugins.push(
        new CompressionWebpackPlugin({
            asset: '[path].gz[query]',
            algorithm: 'gzip',
            test: new RegExp(
                '\\.(' +
                config.build.productionGzipExtensions.join('|') +
                ')$'
            ),
            threshold: 10240,
            minRatio: 0.8
        })
    )
}

if (config.build.bundleAnalyzerReport) {
    const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin
    webpackConfig.plugins.push(new BundleAnalyzerPlugin())
}

module.exports = webpackConfig
