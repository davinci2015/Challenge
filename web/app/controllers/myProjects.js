angular.module('application')
	.controller('myProjects', ['$http', function($http) {
	    var controller = this;
	    controller.projectList = {};

	    $http.get('ajax/get_projects.php')
	        .success(function(data, status, headers, config) {
	            console.log(data);
	            controller.projectList = data;
	        })
	        .error(function(data, status, headers, config) {
	            console.log("Error while fetching data"  + data);
	        });
	}]);
