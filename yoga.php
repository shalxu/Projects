<!DOCTYPE html>
<html>
<head>
	<title>Yoga Quizer</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script>

	function shuffleNums()
	{
		var nums=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20];
		for (var i = 20; i >= 0; i--)
		{
			var temp=nums[i];
			var tempIndex=Math.floor(Math.random()*21);
			temp=nums[i];
			nums[i]=nums[tempIndex];
			nums[tempIndex]=temp;
		}
		return nums;
	}

	var state="checkUser";
	var username="";
	var quizMode=0;
	var quizCount=0;
	var score=0;
	var yogaNames = '['+
	'{"id":0,"english":"Downward Facing Dog","hindu":"Adho Mukha Svanasana"},'+
	'{"id":1,"english":"Supported Pigeon Pose", "hindu":"Salamba Kapotasana"},'+
	'{"id":2,"english":"Hero Pose", "hindu":"Virasana"},'+
	'{"id":3,"english":"Cobra Pose", "hindu":"Bhujangasana"},'+
	'{"id":4,"english":"Corpose Pose", "hindu":"Savasana"},'+
	'{"id":5,"english":"Tree pose", "hindu":"Vrksasana"},'+
	'{"id":6,"english":"Serpent Vishnu Slept On", "hindu":"Ardha Padma Anantasana"},'+
	'{"id":7,"english":"One Leg Revolved Belly Pose", "hindu":"Eka Pada Jathara Paravarttanasana"},'+
	'{"id":8,"english":"Westward Facing", "hindu":"Paschimottasana"},'+
	'{"id":9,"english":"Reclining Hero Pose", "hindu":"Supta Virasana"},'+
	'{"id":10,"english":"Warrior 3", "hindu":"Virabhadrasana III"},'+
	'{"id":11,"english":"Bound Angle Pose", "hindu":"Baddha Konasana"},'+
	'{"id":12,"english":"Sun Salutation", "hindu":"Sun salutation"},'+
	'{"id":13,"english":"Revolving Half Lotus", "hindu":"Parivrtta Ardha Padmasana"},'+
	'{"id":14,"english":"Scorpio", "hindu":"Vrisikasana"},'+
	'{"id":15,"english":"Mountain", "hindu":"Tadasana"},'+
	'{"id":16,"english":"Cobra (Variation)", "hindu":"Bhujangasana"},'+
	'{"id":17,"english":"Half Revolved Belly Pose", "hindu":"Ardha Jathara Parivarttanasana"},'+
	'{"id":18,"english":"Half Lotus Pose", "hindu":"Ardha Padmasana"},'+
	'{"id":19,"english":"Bound Angle Pose", "hindu":"Baddha Konasana"},'+
	'{"id":20,"english":"Ancient Sage curesed with Crooked limbs", "hindu":"Astavakrasana"}'+
	']';

	//starting state
	function checkUser() {
		console.log("Checking for user");
		var name = "yogaQuizUser=";
		var scoreString = "yogaQuizScore=";
		var ca = document.cookie.split(';');
		for(var i = 0; i <ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				user=c.substring(name.length,c.length);
				document.getElementById("inputUser").style.display="none";
				console.log("User detected!");
				state="ready";
				ready();
			}
			if (c.indexOf(scoreString) == 0) {
				document.getElementById("feedback").innerHTML="Your past scores:"
				var scr=c.substring(scoreString.length,c.length).split("/");
				console.log(scr);
				for(var j=0;j<scr.length;j++)
				{
					var scrs=scr[j].split("<");
					var row= document.getElementById("table").insertRow(0);
					var cell1 = row.insertCell(0);
					var cell2 = row.insertCell(1);

					cell1.innerHTML =scrs[0];
					cell2.innerHTML = scrs[1];
				}
			}
		}
		if(state!="ready")
		{
			state="enterUser";
			document.getElementById("inputUser").style.display="inline";
		}
	}

	window.onload = checkUser;

	function userCreated() {
		if(document.getElementById("inputUser").value!="")
		{
			user=document.getElementById("inputUser").value;
			document.cookie = "yogaQuizUser="+document.getElementById("inputUser").value;
			state="ready";
			ready();
			console.log("User created!");
		}
		else
		{
			document.getElementById("msg1").innerHTML="Invalid username!";
		}
	}

	function ready(){
		document.getElementById("user").innerHTML="Welcome, "+user;
		document.getElementById("inputUser").style.display="none";
		document.getElementById("msg1").innerHTML="Please choose your Mode of Quiz!";
		document.getElementById("button2").style.display="inline";
		document.getElementById("button3").style.display="inline";
		document.getElementById("button2").innerHTML="Multiple Choice in English";
		document.getElementById("button3").innerHTML="Right or Wrong in  Sanskrit";
		document.getElementById("feedback").innerHTML="";
	}

	function quiz1(){
		console.log("new question!")
		var json = JSON.parse(yogaNames);
		for(var i=1; i<=4;i++)
		{
			document.getElementById("button"+i).correct=false;
			document.getElementById("button"+i).style.display="inline";
		}
		document.getElementById("msg1").innerHTML="Please choose from the following, the correct English name for the yoga position!";
		var shuffle=shuffleNums();
		var id=shuffle[0];
		console.log("Enquired yoga position is"+id);
		for(var i = 0; i < json.length; i++) {
			var obj = json[i];
			if(obj.id==id)
			{
				document.getElementById("image").src="img/yoga"+id+"b.jpg";
				break;
			}
		}
		var answerCount=Math.floor(Math.random()*4)+1;
		document.getElementById("button"+answerCount).innerHTML=json[id].english;
		document.getElementById("button"+answerCount).correct=true;
		document.getElementById("feedback").style.display="block";
		document.getElementById("quizCounter").style.display="block";
		for(var i=1;i<=4;i++)
		{
			if(i!=answerCount)
			{
				var option=shuffle[i];
				for(var j = 0; j < json.length; j++) {
					var obj = json[j];
					if(obj.id==option)
					{
						document.getElementById("button"+i).innerHTML=obj.english;
					}
				}
			}
		}
		quizCount+=1;
		document.getElementById("quizCounter").innerHTML=quizCount+"/5"; 
	}

	function quiz2(){
		document.getElementById("feedback").style.display="block";
		var shuffle=shuffleNums();
		var id=shuffle[0];
		var json = JSON.parse(yogaNames);
		document.getElementById("image").src="img/yoga"+id+"b.jpg";
		var correct=Math.round(Math.random());
		document.getElementById("button2").style.display="inline";
		document.getElementById("button3").style.display="inline";
		document.getElementById("button2").innerHTML="YES";
		document.getElementById("button3").innerHTML="NO";
		document.getElementById("button1").style.display="none";
		document.getElementById("button4").style.display="none";
		if(correct==1)
		{
			document.getElementById("msg1").innerHTML="Please judge if the following yoga position is " +json[shuffle[0]].hindu;
			document.getElementById("button3").correct=false;
			document.getElementById("button2").correct=true;
		}
		else{
			document.getElementById("msg1").innerHTML="Please judge if the following yoga position is " +json[shuffle[1]].hindu;
			document.getElementById("button2").correct=false;
			document.getElementById("button3").correct=true;
		}
		quizCount+=1;
		document.getElementById("quizCounter").innerHTML=quizCount+"/5"; 
		
	}

	function checkAnswer(choice){
		if((document.getElementById(choice)).correct==true)
		{
			score+=1;
			document.getElementById("feedback").innerHTML="✔";
		}
		else
		{
			document.getElementById("feedback").innerHTML="✗";
		}
	}

	function showResults(){
		document.getElementById("feedback").innerHTML="Your overall score:"+score+"/5";
		document.getElementById("msg1").innerHTML="";
		document.getElementById("image").style.display="none";
		document.getElementById("quizCounter").style.display="none";
		document.getElementById("buttons").style.display="none";
		var utc = new Date().toJSON().slice(0,10);
		var scoreString = "yogaQuizScore=";
		var ca = document.cookie.split(';');
		var scoreChecked=false;
		for(var i = 0; i <ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') {
				c = c.substring(1);
			}
			if (c.indexOf(scoreString) == 0) {
				document.cookie = scoreString+c.substring(scoreString.length,c.length)+"/"+utc+"<"+score;
				console.log(scoreString+c.substring(scoreString.length,c.length)+"/"+utc+"<"+score);
				scoreChecked=true;
			}
		}
		if(scoreChecked==false){
			document.cookie = scoreString+utc+"<"+score;
			console.log(scoreString+utc+"<"+score);
		}
	}

	function changeState(choice) {
		if(state=="enterUser") userCreated();
		else if(state=='ready'){
			document.getElementById("table").style.display="none";
			document.getElementById("feedback").innerHTML="";
			state="quiz";
			switch (choice){
				case 'button2':
				quizMode=1;
				quiz1();
				break;
				case 'button3':
				quizMode=2;
				quiz2();
				break;
			}
		}
		else if (state=='quiz'){
			checkAnswer(choice);
			if(quizCount!=5)
			{
				if(quizMode==1) quiz1();
				else if(quizMode==2) quiz2();
			}
			else {
				quizCount+=1;
				checkAnswer(choice);
				state="showResults";
				showResults();
			}
		}
	}
	</script>
</head>
<style>
#buttons{
	display:inline;
}

#button1{
	display:none;
}

#button2{
	display:none;
}

#button4{
	display:none;
}

button{
	margin:10px;
}

#logo{
	color:#A7D6FF;
}

#header{
	display:flex;
}

#user{
	float:right;
	color:#0A2740;
	margin:auto;
	font-family:"Courier New";
	font-weight: bold;
}


#page{
	max-width:60%; 
	height:100%;
	margin:auto;
	padding:30px;
	background-color:white;
}

#feedback{
	color: orange;
	font-size: 30px;
	display: inline;
}

#quizCounter{
	display: inline;
	font-size: 30px;
	color: MidnightBlue;

}

#msg{
	font-size:15px;
}

body{
	background-color:#DDF2FF;
	height:768px;
}

input{
	padding:5px;
	margin:10px;
}


img{
	max-height: 400px;
	max-width:100%;
	margin: auto;
}

th, tr, td {
    padding: 15px;
    text-align: left;
    border: 1px solid #A5DEFF;
}

</style>
<body>
	<div id="page">
		<div class="col-md-12" id="header" name="header">
			<h1 id="logo" name="logo">
				YOGA QUIZER!
			</h1>
			<h3 id="user" name="user">
				Welcome, user!
			</h3>
		</div>

		<form id="form" action='<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>' method="post">
			<div class="col-md-12">
				<div id="feedback" name="feedback" ></div>
				<div id="quizCounter" name="quizCounter"></div>
			</div>
				<table id="table" name="table"></table>
			<div name="msg1" id="msg1" class="col-md-12 msg">
				Enter your username:
				<input id="inputUser" name="inputUser" value="YogaWarrior"></input>
			</div>
			<div class="col-md-12">
				<img id="image" name="image"></img>
			</div>
			<div class="col-md-12" id="buttons" name="buttons">
				<button id="button1" correct=false; name="button1" type="button" class="btn btn-info" onclick="changeState('button1')"/>	
				<button id="button2" correct=false; name="button2" type="button" class="btn btn-info" onclick="changeState('button2')"/>
				<button id="button3" correct=false; name="button3" type="button" class="btn btn-info" onclick="changeState('button3')"> Ready!</button>
				<button id="button4" correct=false; name="button4" type="button" class="btn btn-info" onclick="changeState('button4')"/>
			</div>
		</form>
		<div>Thanks to materials provided by www.yogacards.com</div>
	</div>

</body>
</html> 
