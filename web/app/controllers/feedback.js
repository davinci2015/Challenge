angular.module('application')
	.controller('feedback', ['$http', '$routeParams', function($http, $routeParams){
		var controller = this;
		controller.feedbackList = [];
		controller.csv_link = 'ajax/get_evaluation_data.php?project_id='+$routeParams.id;

		$http.get('ajax/get_feedback.php?project_id=' + $routeParams.id)
			.success(function(data)
			{
				console.log(data);
				controller.feedbackList = data;
			})
			.error(function(data){
				console.log(data);
			});
	}])