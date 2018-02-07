<template>
	<div class="cov-vue-date" :class="option.wrapperClass ? option.wrapperClass : {}">
		<div class="datepickbox">
			<input type="text" title="input date" class="cov-datepicker" readonly="readonly"
				   :placeholder="option.placeholder" v-model="date.time" :required="required" @click="showCheck"
				   @focus="showCheck" :style="option.inputStyle ? option.inputStyle : {}"
				   :class="option.inputClass ? option.inputClass : {}"/>
		</div>
		<div class="datepicker-overlay" v-if="showInfo.check" @click="dismiss($event)"
			 :style="{'background' : option.overlayOpacity? 'rgba(0,0,0,'+option.overlayOpacity+')' : 'rgba(0,0,0,0.5)'}">
			<div class="cov-date-body" :style="{'background-color': option.color ? option.color.header : '#3f51b5'}">
				<div class="cov-date-monthly">
					<div class="cov-date-previous" @click="nextMonth('pre')">«</div>
					<div class="cov-date-caption" :style="{'color': option.color ? option.color.headerText : '#fff'}">
						<span @click="showYear"><small>{{checked.year}}</small>年</span>
						<br>
						<span @click="showMonth">{{displayInfo.month}}</span>
					</div>
					<div class="cov-date-next" @click="nextMonth('next')">»</div>
				</div>
				<div class="cov-date-box" v-if="showInfo.day">
					<div class="cov-picker-box">
						<div class="week">
							<ul>
								<li v-for="weekie in library.week">{{weekie}}</li>
							</ul>
						</div>
						<div class="day" v-for="day,index in dayList" :key="index" @click="checkDay(day)"
							 :class="{'checked':day.checked,'unavailable':day.unavailable,'passive-day': !(day.inMonth)}"
							 :style="day.checked ? (option.color && option.color.checkedDay ? { background: option.color.checkedDay } : { background: '#F50057' }) : {}">
							{{day.value}}
						</div>
					</div>
				</div>
				<div class="cov-date-box list-box" v-if="showInfo.year">
					<div class="cov-picker-box date-list" id="yearList">
						<div class="date-item" v-for="yearItem,index in library.year" :key="index"
							 @click="setYear(yearItem)">{{yearItem}}
						</div>
					</div>
				</div>
				<div class="cov-date-box list-box" v-if="showInfo.month">
					<div class="cov-picker-box date-list">
						<div class="date-item" v-for="monthItem,index in library.month" :key="index"
							 @click="setMonth(monthItem)">{{monthItem}}
						</div>
					</div>
				</div>
				<div class="cov-date-box list-box" v-if="showInfo.hour">
					<div class="cov-picker-box date-list">
						<div class="watch-box">
							<div class="hour-box">
								<div class="mui-pciker-rule mui-pciker-rule-ft"></div>
								<ul>
									<li class="hour-item" v-for="hitem in hours" @click="setTime('hour', hitem, hours)"
										:class="{'active':hitem.checked}">{{hitem.value}}
									</li>
								</ul>
							</div>
							<div class="min-box">
								<div class="mui-pciker-rule mui-pciker-rule-ft"></div>
								<div class="min-item" v-for="mitem in mins" @click="setTime('min',mitem, mins)"
									 :class="{'active':mitem.checked}">{{mitem.value}}
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="button-box">
					<span @click="showInfo.check=false">{{option.buttons? option.buttons.cancel : 'Cancel' }}</span>
					<span @click="picked">{{option.buttons? option.buttons.ok : 'Ok'}}</span>
				</div>
			</div>
		</div>
	</div>
</template>

<script type="text/ecmascript-6">
    import moment from 'moment'
    export default {
        name: 'datepicker',
        props: {
            required: false,
            date: {
                type: Object,
                required: true
            },
            option: {
                type: Object,
                default () {
                    return {
                        type: 'day',
                        SundayFirst: false,
                        week: ['一', '二', '三', '四', '五', '六', '日'],
                        month: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                        format: 'YYYY-MM-DD',
                        color: {
                            checked: '#F50057',
                            header: '#3f51b5',
                            headerText: '#fff'
                        },
                        wrapperClass: '',
                        inputClass: '',
                        inputStyle: {
                            'display': 'inline-block',
                            'padding': '6px',
                            'line-height': '22px',
                            'font-size': '16px',
                            'border': '2px solid #fff',
                            'box-shadow': '0 1px 3px 0 rgba(0, 0, 0, 0.2)',
                            'border-radius': '2px',
                            'color': '#5F5F5F'
                        },
                        placeholder: 'when?',
                        buttons: {
                            ok: '确定',
                            cancel: '取消'
                        },
                        overlayOpacity: 0.5,
                        dismissible: true
                    }
                }
            },
            limit: {
                type: Array,
                default () {
                    return []
                }
            }
        },
        data () {
            function hours() {
                let list = []
                let hour = 24
                while (hour > 0) {
                    hour--
                    list.push({
                        checked: false,
                        value: hour < 10 ? '0' + hour : hour
                    })
                }
                return list
            }

            function mins() {
                let list = []
                let min = 60
                while (min > 0) {
                    min--
                    list.push({
                        checked: false,
                        value: min < 10 ? '0' + min : min
                    })
                }
                return list
            }

            return {
                hours: hours(),
                mins: mins(),
                showInfo: {
                    hour: false,
                    day: false,
                    month: false,
                    year: false,
                    check: false
                },
                displayInfo: {
                    month: ''
                },
                library: {
                    week: this.option.week,
                    month: this.option.month,
                    year: []
                },
                checked: {
                    oldtime: '',
                    currentMoment: null,
                    year: '',
                    month: '',
                    day: '',
                    hour: '00',
                    min: '00'
                },
                dayList: [],
                selectedDays: []
            }
        },
        methods: {
            pad (n) {
                n = Math.floor(n)
                return n < 10 ? '0' + n : n
            },
            nextMonth (type) {
                let next = null
                type === 'next' ? next = moment(this.checked.currentMoment).add(1, 'M') : next = moment(this.checked.currentMoment).add(-1, 'M')
                this.showDay(next)
            },
            showDay (time) {
                if (time === undefined || !Date.parse(time)) {
                    this.checked.currentMoment = moment()
                } else {
                    this.checked.currentMoment = moment(time, this.option.format)
                }
                this.showOne('day')
                this.checked.year = moment(this.checked.currentMoment).format('YYYY')
                this.checked.month = moment(this.checked.currentMoment).format('MM')
                this.checked.day = moment(this.checked.currentMoment).format('DD')
                this.displayInfo.month = this.library.month[moment(this.checked.currentMoment).month()]
                let days = []
                let currentMoment = this.checked.currentMoment
                let firstDay = moment(currentMoment).date(1).day()
                // gettting previous and next month
                // let currentMonth = moment(currentMoment)
                let previousMonth = moment(currentMoment)
                let nextMonth = moment(currentMoment)
                nextMonth.add(1, 'months')
                previousMonth.subtract(1, 'months')
                let monthDays = moment(currentMoment).daysInMonth()
                let oldtime = this.checked.oldtime
                for (let i = 1; i <= monthDays; ++i) {
                    days.push({
                        value: i,
                        inMonth: true,
                        unavailable: false,
                        checked: false,
                        moment: moment(currentMoment).date(i)
                    })
                    if (i === Math.ceil(moment(currentMoment).format('D')) && moment(oldtime, this.option.format).year() === moment(currentMoment).year() && moment(oldtime, this.option.format).month() === moment(currentMoment).month()) {
                        days[i - 1].checked = true
                    }
                    this.checkBySelectDays(i, days)
                }
                if (firstDay === 0) firstDay = 7
                for (let i = 0; i < firstDay - (this.option.SundayFirst ? 0 : 1); i++) {
                    let passiveDay = {
                        value: previousMonth.daysInMonth() - (i),
                        inMonth: false,
                        action: 'previous',
                        unavailable: false,
                        checked: false,
                        moment: moment(currentMoment).date(1).subtract(i + 1, 'days')
                    }
                    days.unshift(passiveDay)
                }
                var passiveDaysAtFinal = 42 - days.length
                for (let i = 1; i <= passiveDaysAtFinal; i++) {
                    let passiveDay = {
                        value: i,
                        inMonth: false,
                        action: 'next',
                        unavailable: false,
                        checked: false,
                        moment: moment(currentMoment).add(1, 'months').date(i)
                    }
                    days.push(passiveDay)
                }
                if (this.limit.length > 0) {
                    for (let li of this.limit) {
                        switch (li.type) {
                            case 'fromto':
                                days = this.limitFromTo(li, days)
                                break
                            case 'weekday':
                                days = this.limitWeekDay(li, days)
                                break
                        }
                    }
                }
                this.dayList = days
            },
            checkBySelectDays (d, days) {
                this.selectedDays.forEach(day => {
                    if (this.checked.year === moment(day).format('YYYY') && this.checked.month === moment(day).format('MM') && d === Math.ceil(moment(day).format('D'))) {
                        days[d - 1].checked = true
                    }
                })
            },
            limitWeekDay (limit, days) {
                days.map((day) => {
                    if (limit.available.indexOf(Math.floor(day.moment.format('d'))) === -1) {
                        day.unavailable = true
                    }
                })
                return days
            },
            limitFromTo (limit, days) {
                if (limit.from || limit.to) {
                    days.map((day) => {
                        if (this.getLimitCondition(limit, day)) {
                            day.unavailable = true
                        }
                    })
                }
                return days
            },
            getLimitCondition (limit, day) {
                let tmpMoment = moment(day.moment.year() + '-' + this.pad(day.moment.month() + 1) + '-' + this.pad(day.value))
                if (limit.from && !limit.to) {
                    return !tmpMoment.isAfter(limit.from)
                } else if (!limit.from && limit.to) {
                    return !tmpMoment.isBefore(limit.to)
                } else {
                    return !tmpMoment.isBetween(limit.from, limit.to)
                }
            },
            checkDay (obj) {
                if (obj.unavailable || obj.value === '') {
                    return false
                }
                if (!(obj.inMonth)) {
                    this.nextMonth(obj.action)
                }
                if (this.option.type === 'day' || this.option.type === 'min') {
                    this.dayList.forEach((x) => {
                        x.checked = false
                    })
                    this.checked.day = this.pad(obj.value)
                    obj.checked = true
                } else {
                    let day = this.pad(obj.value)
                    let ctime = this.checked.year + '-' + this.checked.month + '-' + day
                    if (obj.checked === true) {
                        obj.checked = false
                        this.selectedDays.$remove(ctime)
                    } else {
                        this.selectedDays.push(ctime)
                        obj.checked = true
                    }
                }
                switch (this.option.type) {
                    case 'day':
                        this.picked()
                        break
                    case 'min':
                        this.showOne('hour')
                        // shift activated time items to visible position.
                        this.shiftActTime()
                        break
                }
            },
            showYear () {
                let year = moment(this.checked.currentMoment).year()
                this.library.year = []
                let yearTmp = []
                for (let i = year - 100; i < year + 5; ++i) {
                    yearTmp.push(i)
                }
                this.library.year = yearTmp
                this.showOne('year')
                this.$nextTick(() => {
                    let listDom = this.document.getElementById('yearList')
                    listDom.scrollTop = (listDom.scrollHeight - 100)
                    this.addYear()
                })
            },
            showOne (type) {
                switch (type) {
                    case 'year':
                        this.showInfo.hour = false
                        this.showInfo.day = false
                        this.showInfo.year = true
                        this.showInfo.month = false
                        break
                    case 'month':
                        this.showInfo.hour = false
                        this.showInfo.day = false
                        this.showInfo.year = false
                        this.showInfo.month = true
                        break
                    case 'day':
                        this.showInfo.hour = false
                        this.showInfo.day = true
                        this.showInfo.year = false
                        this.showInfo.month = false
                        break
                    case 'hour':
                        this.showInfo.hour = true
                        this.showInfo.day = false
                        this.showInfo.year = false
                        this.showInfo.month = false
                        break
                    default:
                        this.showInfo.day = true
                        this.showInfo.year = false
                        this.showInfo.month = false
                        this.showInfo.hour = false
                }
            },
            showMonth () {
                this.showOne('month')
            },
            addYear () {
                let listDom = this.document.getElementById('yearList')
                listDom.addEventListener('scroll', (e) => {
                    if (listDom.scrollTop < listDom.scrollHeight - 100) {
                        let len = this.library.year.length
                        let lastYear = this.library.year[len - 1]
                        this.library.year.push(lastYear + 1)
                    }
                }, false)
            },
            setYear (year) {
                this.checked.currentMoment = moment(year + '-' + this.checked.month + '-' + this.checked.day)
                this.showDay(this.checked.currentMoment)
            },
            setMonth (month) {
                let mo = (this.library.month.indexOf(month) + 1)
                if (mo < 10) {
                    mo = '0' + '' + mo
                }
                this.checked.currentMoment = moment(this.checked.year + '-' + mo + '-' + this.checked.day)
                this.showDay(this.checked.currentMoment)
            },
            showCheck () {
                if (this.date.time === '') {
                    this.showDay()
                } else {
                    if (this.option.type === 'day' || this.option.type === 'min') {
                        this.checked.oldtime = this.date.time
                        this.showDay(this.date.time)
                    } else {
                        this.selectedDays = JSON.parse(this.date.time)
                        if (this.selectedDays.length) {
                            this.checked.oldtime = this.selectedDays[0]
                            this.showDay(this.selectedDays[0])
                        } else {
                            this.showDay()
                        }
                    }
                }
                this.showInfo.check = true
            },
            setTime (type, obj, list) {
                for (let item of list) {
                    item.checked = false
                    if (item.value === obj.value) {
                        item.checked = true
                        this.checked[type] = item.value
                    }
                }
            },
            picked () {
                if (this.option.type === 'day' || this.option.type === 'min') {
                    let ctime = this.checked.year + '-' + this.checked.month + '-' + this.checked.day + ' ' + this.checked.hour + ':' + this.checked.min
                    this.checked.currentMoment = moment(ctime, 'YYYY-MM-DD HH:mm')
                    this.date.time = moment(this.checked.currentMoment).format(this.option.format)
                } else {
                    this.date.time = JSON.stringify(this.selectedDays)
                }
                this.showInfo.check = false
                this.$emit('change', this.date.time)
            },
            dismiss (evt) {
                if (evt.target.className === 'datepicker-overlay') {
                    if (this.option.dismissible === undefined || this.option.dismissible) {
                        this.showInfo.check = false
                        this.$emit('cancel')
                    }
                }
            },
            shiftActTime () {
                // shift activated time items to visible position.
                this.$nextTick(() => {
                    if (!this.document.querySelector('.hour-item.active')) {
                        return false
                    }
                    this.document.querySelector('.hour-box').scrollTop = (this.document.querySelector('.hour-item.active').offsetTop || 0) - 250
                    this.document.querySelector('.min-box').scrollTop = (this.document.querySelector('.min-item.active').offsetTop || 0) - 250
                })
            }
        },
		created () {

		},
		mounted () {
            this.document = this.$doc(this)
		}
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "../../common/stylus/mixin.styl";
	@import "datepicker.styl";
</style>