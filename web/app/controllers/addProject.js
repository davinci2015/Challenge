angular.module('application')
	.controller('addProject', ['$http', '$location', function($http, $location){
		var controller = this;
		controller.projectData = [];
		controller.public = '';

		controller.checkVisibility = function()
		{
			return 'false' === controller.public;
		}

		controller.AddGroup = function()
		{
			controller.projectData.push({
				groupName: ''
			});
		}
		controller.AddProject = function()
		{
			if(controller.public == 'true')
				controller.public = 1;
			else
			{
				controller.public = 0;
				controller.privateOpen = controller.privateOpen == 'privateOpen' ? 1 : 0;
			}
			if(controller.email != undefined)
			{
				var email = controller.email.split(';');
				controller.emailRegInvitation = [];
				controller.emailNoregInvitation = [];
				controller.toast = "";
				$http({
		          method  : 'POST',
		          url     : 'ajax/check_mail.php',
		          data    : email, 
		          headers : {'Content-Type': 'application/x-www-form-urlencoded'} 
		         })
					.success(function(data)
					{
						console.log(data);
						for(var i = 0; i < data.length; i++)
						{
							if(data[i]) controller.emailRegInvitation.push(email[i]);
							else 
							{
								controller.emailNoregInvitation.push(email[i]);
								controller.toast += email[i] + " ";	
							}
						}
						if(controller.toast != "")
							Materialize.toast('Unregistered user will get invitation to application - ' + controller.toast, 6000);
						controller.projectData.push({
							name: controller.projectName,
							desc: controller.projectDesc,
							public: controller.public,
							open: controller.privateOpen,
							emailReg: controller.emailRegInvitation,
							emailNoreg: controller.emailNoregInvitation
						});
						console.log(controller.projectData);
						$http.post('ajax/addProject.php', controller.projectData)
			                .success(function(data){
			                	console.log(data);
			                })
			                .error(function(){
			                    alert("Error. Try Again.");
			                });
					})
					.error(function(data){
						console.log(data);
					})
			}
			else
			{
				controller.projectData.push({
							name: controller.projectName,
							desc: controller.projectDesc,
							public: controller.public,
							open: null,
							emailReg: null,
							emailNoreg: null
				});
				console.log(controller.projectData);
				$http.post('ajax/addProject.php', controller.projectData)
	                .success(function(data){
	                	$location.path('/my-projects');
	                })
	                .error(function(){
	                    alert("Error. Try Again.");
	                });
			}
			
		}
	}])