angular.module('application')
    .config(function($routeProvider){
        $routeProvider
            .when('/my-projects', {
                controller: 'myProjects',
                controllerAs: 'projects',
                resolve: {"check" : check},
                templateUrl: 'app/views/my-projects.html'
            })
            .when('/add-project',{
                controller: 'addProject',
                controllerAs: 'project',
                resolve: {"check" : check},
                templateUrl: 'app/views/add-project.html',
            })
            .when('/feedback/:id', {
                controller: 'feedback',
                controllerAs: 'feedback',
                resolve: {"check" : check},
                templateUrl: 'app/views/feedback.html',
            })
            .when('/login', {
                templateUrl: 'app/views/login.html',
                controller: 'login',
                controllerAs: 'log'
            })
            .when('/logout',{
                resolve: {"logout" : logout},
                redirectTo: '/login'
            })
            .when('/home',{
                controller: 'rank',
                controllerAs: 'rank',
                templateUrl: 'app/views/home.html'
            })
            .otherwise( {
            	redirectTo: '/home'
            });
    });

var check = function($location, $rootScope){
                if(!$rootScope.loggedIn)
                    $location.path('/login')
            }
var logout = function($location, $rootScope){
                $rootScope.loggedIn = false;
            }

