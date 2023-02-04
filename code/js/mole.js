function init() {
    rankArray = new Array()
    change = true
    moleGap = 2000
}

function clear() {
    go = false
    moleStatus = [{
        "id": "one",
        "pop": false
    }, {
        "id": "two",
        "pop": false
    }, {
        "id": "three",
        "pop": false
    }, {
        "id": "four",
        "pop": false
    }, {
        "id": "five",
        "pop": false
    }, {
        "id": "six",
        "pop": false
    }]
    score = 0
    document.getElementById('score').innerHTML = score
    document.getElementById('time').innerHTML = '0.0.0'
    moleStatus.forEach(mole => {
        document.getElementById(mole.id).style.backgroundImage = "url('img/down.png')"
    });
}

function start(min) {
    if (typeof (clock) == 'undefined') {
        //开始游戏
        go = true
        //打开时钟
        var endTime = new Date()
        endTime.setMinutes(endTime.getMinutes() + min)
        // endTime.setSeconds(endTime.getSeconds() + 10)
        var timeInfo = document.getElementById('time')
        clock = setInterval(
            function () {
                nowTime = new Date()
                var lefttime = parseInt((endTime.getTime() - nowTime.getTime()) / 10);
                if (lefttime <= 0) {
                    end()
                }
                var m = parseInt(lefttime / 6000 % 10);
                var s = parseInt(lefttime / 100 % 60);
                var ms = parseInt(lefttime % 100);
                timeInfo.innerHTML = m + '.' + s + '.' + ms
            }
            , 60
            // , Math.ceil(Math.random() * 100)
        )
        //随机弹出地鼠
        moleControl = setInterval(
            function () {
                var i = Math.floor(Math.random() * 6)
                moleAction(i)
            }
            , moleGap
        )
    }
}

function end() {
    if (typeof (clock) != 'undefined') {
        clearInterval(clock)
        delete clock
    }
    if (typeof (moleControl) != 'undefined') {
        clearInterval(moleControl)
        delete moleControl
    }
    if (score != 0) {
        //输出游戏结束信息
        alert('Your score is ' + score)
        // 添加排行
        rankArray.push(score)
        rankArray.sort()
        rankArray.reverse()
        var list = document.getElementById('rank_list')
        var str = ''
        rankArray.forEach(n => {
            str = str + '<li>' + n + '</li>'
        })
        if (rankArray.length > 5) {
            rankArray.pop()
        }
        list.innerHTML = str
    }
    // 初始化游戏
    clear()
}

function mole(node) {
    if (go) {
        //拿到当前地鼠的状态
        var pop = false
        var index = 0
        for (let i = 0; i < moleStatus.length; i++) {
            if (moleStatus[i].id == node.id) {
                index = i
                pop = moleStatus[i].pop
            }
        }
        //如果是出现的状态
        if (pop) {
            node.style.backgroundImage = "url('img/punch.png')"
            document.getElementById('score').innerHTML = ++score
            //如果速度随分数变化
            if (change) {
                clearInterval(moleControl)
                var t = 1500 - Math.floor(score * 0.5) * 100
                if (t <= 500) {
                    t = 500
                }
                moleControl = setInterval(
                    function () {
                        var i = Math.floor(Math.random() * 6)
                        moleAction(i)
                    }
                    , t
                )
            }

            setTimeout(() => {
                node.style.backgroundImage = "url('img/down.png')"
                moleStatus[index].pop = false
            }, 500);
        }

    }
}

function moleAction(i) {
    if (moleStatus[i].pop) {
        moleStatus[i].pop = false
        document.getElementById(moleStatus[i].id).style.backgroundImage = "url('img/down.png')"
    } else {
        moleStatus[i].pop = true
        document.getElementById(moleStatus[i].id).style.backgroundImage = "url('img/up.png')"
    }
}

function modeNomal() {
    if (!go) {
        change = true
        moleGap = 1500
        var button = document.getElementById('dropbtn')
        button.style.backgroundColor = '#4CAF50'
        button.innerHTML = 'Normal'
    }
}

function modeBaby() {
    if (!go) {
        change = false
        moleGap = 2000
        var button = document.getElementById('dropbtn')
        button.style.backgroundColor = 'pink'
        button.innerHTML = 'Baby'
    }
}

function modeHell() {
    if (!go) {
        change = false
        moleGap = 300
        var button = document.getElementById('dropbtn')
        button.style.backgroundColor = 'darkred'
        button.innerHTML = 'Hell'
    }
}