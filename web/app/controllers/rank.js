angular.module('application')
	.controller('rank', ['$http', function($http){
		var controller = this;
		controller.rankList = [];

		$http.post('ajax/get_rank_list.php', {server_call:1})
			.success(function(data){
				controller.rankList = data;
				console.log(data);
			})
	}]);