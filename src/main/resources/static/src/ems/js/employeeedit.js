window.addEventListener('DOMContentLoaded', function() {
    getAge();
    document.getElementById("birthday").onchange = function() {
        getAge();
    };

    getJoinedAge();
    document.getElementById("joinedDateString").onchange = function() {
        getJoinedAge();
    };
}, false);

function getAge() {
    var birthday = document.getElementById('birthday').value;

    if (birthday) { // Check if birthday is not empty
        var bArr = birthday.split('-');
        var birth = new Date(bArr[0], (bArr[1] - 1), bArr[2]); // 誕生日の日付
        var today = new Date(); // 今日の日付
        var df = today - birth; // 今日と誕生日の差(ミリ秒)
        var age = Math.floor(df / 1000 / 60 / 60 / 24 / 365); // ミリ秒を年に変換(切り捨て)

        document.getElementById('ageDisp').innerText = age;
        document.getElementById('age').value = age;
    } else {
        document.getElementById('ageDisp').innerText = '';
        document.getElementById('age').value = '';
    }
}

function getJoinedAge() {
    var joinedDate = document.getElementById('joinedDateString').value;

    if (joinedDate) { // Check if joinedDate is not empty
        var bArr = joinedDate.split('-');
        var joinedDatetmp = new Date(bArr[0], (bArr[1] - 1), bArr[2]); // 入社日の日付
        var today = new Date(); // 今日の日付
        var df = today - joinedDatetmp; // 今日と入社日の差(ミリ秒)
        var joinedAge = Math.floor(df / 1000 / 60 / 60 / 24 / 365); // ミリ秒を年に変換(切り捨て)

        document.getElementById('joinedAge').innerText = joinedAge;
        document.getElementById('joinedTime').value = joinedAge;
    } else {
        document.getElementById('joinedAge').innerText = '';
        document.getElementById('joinedTime').value = '';
    }
}
