'use strict'
require('./check-versions')()

process.env.NODE_ENV = 'production'

const ora = require('ora') // 实现node命令行环境的loading效果
const rm = require('rimraf') // 创建指定文件夹，若已存在，先删除
const path = require('path')
const chalk = require('chalk') // 颜色插件，改变颜色
const webpack = require('webpack')
const config = require('../config')
const webpackConfig = require('./webpack.prod.conf')

const spinner = ora('building for production...')
spinner.start()

rm(path.join(config.build.assetsRoot, config.build.assetsSubDirectory), err => {
    if (err) throw err
    webpack(webpackConfig, (err, stats) => {
        spinner.stop()
        if (err) throw err
        process.stdout.write(stats.toString({
                colors: true,
                modules: false,
                children: false, // if you are using ts-loader, setting this to true will make tyescript errors show up during build
                chunks: false,
                chunkModules: false
            }) + '\n\n')

        if (stats.hasErrors()) {
            console.log(chalk.red('  Build failed with errors.\n'))
            process.exit(1)
        }

        console.log(chalk.cyan('  Build complete.\n'))
        console.log(chalk.yellow(
            '  Tip: built files are meant to be served over an HTTP server.\n' +
            '  Opening index.html over file:// won\'t work.\n'
        ))
    })
})
