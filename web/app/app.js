angular.module('application', ['ngRoute'])
	.controller('nav', ['$rootScope', function($rootScope){
		var controller = this;
		controller.isActive = function()
		{
			return $rootScope.loggedIn;
		}
		
	}]);