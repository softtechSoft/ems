window.addEventListener('DOMContentLoaded',
function(){
	getAge();
	document.getElementById("birthday").onchange = function() {
		getAge();
	};


	getJoinedTime();
	document.getElementById("joinedDateString").onchange = function() {
		getJoinedTime();
	};

}
,false);

function getAge() {

	var birthday = document.getElementById('birthday').value;

	var bArr = birthday.split('-');
	var birth = new Date(bArr[0], (bArr[1] - 1), bArr[2]); // 誕生日の日付
	var today = new Date(); // 今日の日付
	var df = today - birth; // 今日と誕生日の差(ミリ秒)
	var age = Math.floor(df / 1000 / 60 / 60 / 24 / 365); // ミリ秒を年に変換(切り捨て)

	document.getElementById('age').innerText = age;
	document.getElementByName('age').value = age;
}

function getJoinedTime() {

	var joinedDate = document.getElementById('joinedDateString').value;



	var bArr = joinedDate.split('-');
	var joinedDate = new Date(bArr[0], (bArr[1] - 1), bArr[2]); // 入社日の日付
	var today = new Date(); // 今日の日付
	var df = today - joinedDate; // 今日と入社日の差(ミリ秒)
	var joinedTime = Math.floor(df / 1000 / 60 / 60 / 24 / 365); // ミリ秒を年に変換(切り捨て)

	document.getElementById('joinedTime').innerText = joinedTime;
	document.getElementByName('joinedTime').value = joinedTime;
}




