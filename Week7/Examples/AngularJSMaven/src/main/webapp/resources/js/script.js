/**
 * My scripts!
 */

/*
 * Custom directives must follow the following syntax:
 * Name MUST be camelCased.
 *
 *	Custom directives can return "templates", which are just translated html strings.
 *	You can add restrictions with the following key: "restrict :" followed by the following
 *	values:
 *	A - attribute
 *	E - elements
 *	M - comments
 *	C - class
 *
 *	
 */
app.directive("neatoDirective", function(){
		return {
			restrict : 'AEC',
			template : "<p>Watch out for sneaky brackets</p>"
				}
});
app.controller("initVars", function($scope, $location){
	$scope.clickMe="Click me!";
	$scope.varName="Ryan";
	$scope.myArray2=[{fname:'Robert',lname:'Bobbert'},{fname:'Xavier',lname:'Xobbert'},{fname:'Zack',lname:'Zackson'}];
	$scope.myArray=[0,1,2,3,4,5,6,7];
	$scope.names=["Jake", "Adam", "Atom", "Addim", "Bobbert", "Zack Zackson", "Me", "Xaviers", "Bellas", "Barden Bellas"];
	$scope.curURL = $location.absUrl();
	$scope.timeoutTest = "This is a sentence!";
	$scope.intervalVar = true;

});

app.controller("timeoutControl", function($scope, $timeout){
	$timeout(function(){
		$scope.timeoutTest = "And now this is a different sentence!";
	},5000);
	$timeout(function(){
		$scope.timeoutTest = "This change occurred first!"
	},3000);
});

app.controller("intervalControl", function($scope, $interval){
	$interval(function(){
		if($scope.intervalVar==true){
			$scope.intervalVar = false;
		}else{
			$scope.intervalVar = true;
		}
	},1000);
	
	
});

app.controller("cScope1", function($scope){
	$scope.varScope = "cScope1";
});

app.controller("cScope2", function($scope){
	$scope.varScope = "cScope2";
});

app.controller("rootControl", function($rootScope){
	$rootScope.varScope = "Root!";
});
//Runs when the script is pulled in.
/*app.run(function($rootScope){
	$rootScope.varScope = "Root!";
});*/

app.controller("clickHandler",function($scope, $rootScope){
	$scope.clicked = function(){
		$rootScope.varScope = "Root Changed!";
	};
});

app.filter("bfilter",function(){
	return function(input){
		var output = "";
		for(let i=0; i<input.length; i++){
			if(i==1){
				output += input[i].toUpperCase();
			}
			else{
				output += input[i];
			}
		}
		return output;
	};
});

//Note: This is a singleton typed service
app.service('myCustomServ', function(){
	this.myFunc = function(){
		return "Hello bobbert!";
	};
});

app.controller('customService',function($scope, myCustomServ){
	$scope.effectedVar = myCustomServ.myFunc();
});

//Ajax GET
app.controller('apiControl', function($scope, $http){	
	$scope.sendReq = function(){
		var input = $scope.apiHit;
		$http.get("https://pokeapi.co/api/v2/pokemon/" + input + "/")
		.then(function(response){
			$scope.hitSrc = response.data.sprites.front_shiny;
			$scope.hitResult = null;
			
		}, function(response){
			$scope.hitResult = response.status;
			$scope.hitSrc = null;
		});
	};
	
});
/*
 * Not only get:
 * $http.post
 * put
 * delete
 * head
 * 
 * in cases where you forward data to the webservice:
 * you must pass a data parameter.
 * $http.post(url, data, config)
 * as opposed to:
 * $http.get(url, config)
 * 
 */

/*<li><a href="#welcome">Welcome</a></li>	
<li><a href="#ngrepeat">NG Repeat</a></li>
<li><a href="#cdirective">Custom Directives</a></li>
<li><a href="#formvalid">Form Validation</a></li>
*/
app.config(function($routeProvider, $locationProvider){
	$routeProvider
	.when("/welcome",{
		template : "welcome.html"
	})
	.when("/ngrepeat", {
		templateUrl : "ngRepeat.html"
	})
	.when("/cdirective", {
		templateUrl : "customDirective.html"
	})
	.when("/formvalid", {
		templateUrl : "formValidation.html"
	})
	.when("/", {
		template : "<h1>View goes here!</h1>"
	})
});

