(function () {
    angular.module('loginApp', []);
    var app = angular.module('loginApp',[]);

    app.directive('loginForm', function (){
        return {
            restrict: 'E',
            templateUrl: 'template/login-form.html',
            controller: function($http,$log,$scope){
                this.account={};
                $scope.error_msg="";
                $scope.error_code=0;
                $scope.auth = function(){
                    $log.log('account: ' + JSON.stringify(this.account));
                    $http.post('/login.do',this.account).success(function (data){
                        $log.log(data.code + ","+data.msg);
                        this.response = data;
                        $log.log(this.response.code);
                        //去主页
                        if(data.code == 0){
                            window.location.href="/index.html";
                        }else{
                            $scope.error_code=data.code;
                            $scope.error_msg=data.msg;
                        }
                    });
                }

            },
            controllerAs: 'loginCtrl'
        }
    });

})();
