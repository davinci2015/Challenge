angular.module('application') 
	.controller('login', ['$location', '$rootScope','$http', function($location, $rootScope, $http){
        var controller = this;

        controller.submit = function(){
        	var obj = {'username': controller.username, 'password': controller.password};
        	$http({
	          method  : 'POST',
	          url     : 'challenge/login-web.php',
	          data    : obj, 
	          headers : {'Content-Type': 'application/x-www-form-urlencoded'} 
	         })
            	.success(function(data, name){
            		$rootScope.loggedIn = true;
	                $location.path('/home');
            	})
        }
        controller.logOut = function(){
        	console.log("ee");
        	$rootScope.loggedIn = false;
        	$location.path('/login');
        }
    }])