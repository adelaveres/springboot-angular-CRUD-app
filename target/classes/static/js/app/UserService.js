'use strict';

angular.module('crudApp').factory('UserService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllUsers: loadAllUsers,
                getAllUsers: getAllUsers,
                getUser: getUser,
				getUserAccounts: getUserAccounts,
                createUser: createUser,
                updateUser: updateUser,
                removeUser: removeUser,
				depositSum: depositSum,
				withdrawSum: withdrawSum,
				removeAccount: removeAccount,
				createAccount: createAccount,
				login: login,
				getLoginAccount: getLoginAccount,
				loadAllEmployees: loadAllEmployees,
				getAllEmployees: getAllEmployees,
				createEmployee: createEmployee,
				getEmployee: getEmployee,
				updateEmployee: updateEmployee,
				removeEmployee: removeEmployee
            };

            return factory;

            function loadAllUsers() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all users');
							console.log(response);
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading users');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
			
			function loadAllEmployees(){
				console.log('Fetching all employees');
				var deferred=$q.defer();
				$http.get(urls.EMPLOYEE_SERVICE_API)
					.then(
						function (response) {
							console.log('Fetched successfully all employees');
							console.log(response);
							$localStorage.employees = response.data;
							deferred.resolve(response);
						},
						function (errResponse) {
							console.error('Error while loading employees');
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}
			
            function getAllUsers(){
                return $localStorage.users;
            }
			
			function getAllEmployees(){
				return $localStorage.employees;
			}

            function getUser(id) {
                console.log('Fetching User with id: '+id);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully User with id: '+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id: '+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
			
			function getEmployee(id){
				console.log('Fetching Employee with id: '+id);
				var deferred = $q.defer();
				$http.get(urls.EMPLOYEE_SERVICE_API + id)
					.then(
						function (response) {
							console.log('Fetched successfully Employee with id: '+id);
							deferred.resolve(response.data);
						},
						function (errResponse) {
							console.error('Error while loading employee with id: '+id);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
					
			}
			
			function getLoginAccount(username){
				console.log('Fetching login account for user: '+username);
				var deferred = $q.defer();
				$http.get(urls.LOGIN_SERVICE_API+username)
					.then(
						function(response){
							console.log('Fetched successfully Login Account for user: '+username);
							console.log(response.data);
							deferred.resolve(response.data);
						},
						function(errResponse){
							console.error('Error while fetching Login Account for user: '+username);
							console.log(response.data);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}
			
			function getUserAccounts(id){
				console.log('Fetching accounts for user with id : '+id);
				var deferred = $q.defer();
				$http.get(urls.USER_SERVICE_API+id+'/accounts')
					.then(
						function (response) {
							console.log('Fetched successfully Accounts for user with id: '+id);
							console.log(response.data);
							deferred.resolve(response.data);
						},
						function (errResponse) {
							console.error('Error while fetching Accounts for user with id: '+id);
							console.log(response.data);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}
			
			function login(loginAccount, enteredPassword){
				console.log('Logging in...');
				var deferred = $q.defer();
				$http.put(urls.LOGIN_SERVICE_API+enteredPassword, loginAccount)
					.then(
						function (response){
							deferred.resolve(response.data);
						},
						function (errResponse){
							console.error('Error while logging in user: '+errResponse.data.errorMessage);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}
			
            function createUser(user) {
                console.log('Creating User');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating User : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
			
			function createEmployee(employee){
				console.log('Creating Employee');
				var deferred = $q.defer();
				$http.post(urls.EMPLOYEE_SERVICE_API, employee)
					.then(
						function(response){
							loadAllEmployees();
							deferred.resolve(response.data);
						},
						function(errResponse){
							console.error('Error while creating Employee: '+errResponse.data.errorMessage);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}
			
			function createAccount(account){
				console.log('Creating Account');
				var deferred = $q.defer();
				$http.post(urls.USER_SERVICE_API+'/account', account)
					.then(
						function(response){
							deferred.resolve(response.data);
						},
						function(errResponse){
							console.error('Error while creating Account: '+errResponse.data.errorMessage);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}

            function updateUser(user, id) {
                console.log('Updating User with id '+id);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating User with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
			
			function updateEmployee(employee, id){
				console.log('Updating Employee with id '+id);
				console.log(employee);
				var deferred = $q.defer();
				$http.put(urls.EMPLOYEE_SERVICE_API + id, employee)
					.then(
						function (response){
							loadAllEmployees();
							deferred.resolve(response.data);
						},
						function (errResponse){
							console.error('Error while updating Employee with id :'+id);
                            deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}
			
			function depositSum(accountNo, depositedSum){
				console.log('Depositing '+depositedSum+' to account '+accountNo);
				var deferred = $q.defer();
				$http.put(urls.USER_SERVICE_API+'/account/'+accountNo+'/deposit', depositedSum)
					.then(
						function(response){
							deferred.resolve(response.data);
						},
						function(errResponse){
							console.error('Error while depositing in account :'+accountNo);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}

			function withdrawSum(accountNo, withdrawnSum){
				console.log('Withdrawing '+withdrawnSum+' from account '+accountNo);
				var deferred = $q.defer();
				$http.put(urls.USER_SERVICE_API+'/account/'+accountNo+'/withdraw', withdrawnSum)
					.then(
						function(response){
							deferred.resolve(response.data);
						},
						function(errResponse){
							console.error('Error while withdrawing from account :'+accountNo);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}

			
            function removeUser(id) {
                console.log('Removing User with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing User with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
			function removeEmployee(id){
				console.log('Removing Employee with id '+id);
				var deferred = $q.defer();
				$http.delete(urls.EMPLOYEE_SERVICE_API + id)
					.then( 
						function (response) {
                            loadAllEmployees();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Employee with id :'+id);
                            deferred.reject(errResponse);
                        }
					);
				return deferred.promise;
			}
			
			function removeAccount(accountId){
				console.log('Removing Account no '+accountId);
				var deferred = $q.defer();
				$http.delete(urls.USER_SERVICE_API+'/account/'+accountId)
					.then(
						function(response){
							deferred.resolve(response.data);
						},
						function(errResponse){
							console.error('Error while deleting Account no: '+accountId);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}

        }
    ]);