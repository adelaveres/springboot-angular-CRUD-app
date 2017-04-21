'use strict';

angular.module('crudApp').controller('UserController',
    ['UserService', '$scope',  function( UserService, $scope) {
		
		$scope.form = {};
		
        var self = this;
        self.user = {};
		self.userWithAccount = {};
		self.userToUpdate = {};
		self.employeeToUpdate = {};
        self.users=[];
		self.account= {};
		self.accounts=[];
		self.employee={};
		self.employeeName;
		self.employeeCNP;
		self.employeeAddress;
		self.loginAccountUsername;
		self.loginAccountPassword;
		self.loginAccount= {};
		self.addingUser;
		self.updatingUser;
		self.addingEmployee;
		self.updatingEmployee;
		self.displayAccounts = false;
		self.displayClients = true;
		self.displayCreateAccount = false;
		self.displayLogin = true;
		self.displayEmployeeView = false;
		self.displayAdminView = false;
		self.depositSum = [];
		self.withdrawnSum = [];
		self.fromAccountNo;
		self.toAccountNo;
		self.sumToTransfer;
		self.accountTypes= ['saving', 'spending'];
		
        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
        self.editUser = editUser;
		self.editAccounts = editAccounts;
		self.doneWithAccounts = doneWithAccounts;
        self.reset = reset;
		self.isValidCNP = isValidCNP;
		self.addUser = addUser;
		self.deposit = deposit;
		self.withdraw = withdraw;
		self.transfer = transfer;
		self.removeAccount = removeAccount;
		self.addAccount = addAccount;
		self.toggleDisplayCreateAccount = toggleDisplayCreateAccount;
		self.login= login;
		self.displayLoginPage = displayLoginPage;
		self.displayEmployeePage = displayEmployeePage;
		self.displayAdminPage = displayAdminPage;
		self.submitEmployee = submitEmployee;
		self.employeeReset = employeeReset;
		self.getAllEmployees = getAllEmployees;
		self.addEmployee = addEmployee;
		self.updateEmployee = updateEmployee;
		self.deleteEmployee = deleteEmployee;
		self.isValidEmployeeCNP = isValidEmployeeCNP;
		self.createEmployee = createEmployee;
		self.editEmployee = editEmployee;
		
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
		
		
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
		
		function displayLoginPage(){
			self.displayLogin = true;
			self.displayEmployeeView = false;
			self.displayAdminView = false;
		}
		function displayEmployeePage(){
			self.displayLogin = false;
			self.displayEmployeeView = true;
			self.displayAdminView = false;
		}
		function displayAdminPage(){
			self.displayLogin = false;
			self.displayEmployeeView = false;
			self.displayAdminView = true;
		}

		function toggleDisplayCreateAccount(){
			if(self.displayCreateAccount === true)
				self.displayCreateAccount = false;
			else
				self.displayCreateAccount = true;
		}
		
		function addAccount(){
			console.log('Submitting adding account request');
			self.account.creationDate = new Date(); //current date
			self.account.cnp = self.userWithAccount.cnp;
			createAccount(self.account);			
		}
		
		function submitEmployee(){
			console.log('Submitting');
			if(self.addingEmployee == true){
				addEmployee();
				self.addingEmployee = false; 
			}
			else{
				if(self.updatingEmployee == true){
					updateEmployee(self.employee, self.employee.employeeId);
					self.updatingEmployee = false;
				}
			}
		}
		
        function submit() {
            console.log('Submitting');
			if(self.addingUser == true){
				addUser();
				self.addingUser = false;
			}
			else{
				if(self.updatingUser == true){
					updateUser(self.user, self.user.id);
					self.updatingUser = false;
				} 
			}
        }
		
		function isValidCNP(cnp, formerCNP){	//no duplicate CNP
			if(cnp !== undefined && cnp !== null){
				var users = getAllUsers();
				for(var key in users){
					if(users.hasOwnProperty(key)){
						console.log( "\n" + key + " -> " + users[key].cnp );
						if( users[key].cnp === cnp &&  users[key].cnp !== formerCNP)
							return false;
					}
				}
			}
			else{
				return false;
			}
			return true;
		}
		
		function isValidEmployeeCNP(cnp, formerCNP){	//no duplicate CNP
			if(cnp !== undefined && cnp !== null){
				var employees = getAllEmployees();
				for(var key in employees){
					if(employees.hasOwnProperty(key)){
						console.log( "\n" + key + " -> " + employees[key].cnp );
						if( employees[key].cnp === cnp &&  employees[key].cnp !== formerCNP)
							return false;
					}
				}
			}
			else{
				return false;
			}
			return true;
		}
		
		function addUser(){
			console.log('About to add user');
			if( isValidCNP(self.user.cnp, null) ){
				console.log('Saving New User', self.user);
                createUser(self.user);
			}
			else{
				self.errorMessage = 'Identical CNP already exists.';
				self.successMessage = '';
			}
		}
		
		function addEmployee(){
			console.log('Aboout to add employee');
			if( isValidEmployeeCNP(self.employee.cnp, null)){
				console.log('Saving new Employee', self.employee);
				createEmployee(self.employee);
			}
			else{
				self.errorMessage = 'Identical CNP already exists.';
				self.successMessage = '';
			}
		}
		
        function createUser(user) {
            console.log('About to create user');
            UserService.createUser(user)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        self.successMessage = 'User created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.user={};
                        //$scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
		
		function createEmployee(employee){
			console.log('About to create employee');
			UserService.createEmployee(employee)
				.then(
					function(response){
						console.log('Employee created successfully');
						self.successMessage = 'Employee created successfully';
						self.errorMessage='';
                        self.done = true;
						self.employee={};
						$scope.form.myEmployeeForm.$setPristine();
					},
					function(errResponse){
						console.error('Error while creating Employee');
						self.errorMessage = errResponse.data.errorMessage;
                        self.successMessage='';
					}
				);
		}

		function createAccount(account){
			console.log('About to create account');
			UserService.createAccount(account)
				.then(
					function(response){
						console.log('Account created successfully');
						self.successMessage='Account created successfully';
						self.errorMessage='';
						self.done=true;
						self.account={};
						$scope.myForm.$setPristine();
					},
					function(errResponse){
						console.error('Error while creating Account');
                        self.errorMessage = errResponse.data.errorMessage;
                        self.successMessage='';
					}
				);
		}
		
		
        function updateUser(user, id){
            console.log('About to update user');			
			
			UserService.getUser(id).then(
                function (user) {
                    self.userToUpdate = user;
                },
                function (errResponse) {
                    console.error('Error while loading user ' + id + ', Error : ' + errResponse.data);
					self.errorMessage='No such user exists to update.';
					self.successMessage='';
                }
            );
			
			if( isValidCNP(self.user.cnp, self.userToUpdate.cnp) ){
				console.log('Updating User - valid PNC', self.user);
				UserService.updateUser(user, id)
					.then(
						function (response){
							console.log('User updated successfully');
							self.successMessage='User updated successfully';
							self.errorMessage='';
							self.done = true;
							$scope.myForm.$setPristine();
						},
						function(errResponse){
							console.error('Error while updating User');
							self.errorMessage='Error while updating User: '+errResponse.data;
							self.successMessage='';
						}
					);
			}
			else{
				self.errorMessage = 'Identical CNP already exists.';
				self.successMessage = '';
			}
        }
		
		function updateEmployee(employee, id) {
			console.log('About to update employee: ');			
			console.log(employee);
			
			var employeeAboutToUpdate = {};
			
			UserService.getEmployee(id).then(
                function (employee) {
                    self.employeeToUpdate = employee;
					employeeAboutToUpdate = employee;
					
					if( isValidEmployeeCNP(self.employee.cnp, employeeAboutToUpdate.cnp) ){
				console.log('Updating Employee - valid PNC', self.employee);
				UserService.updateEmployee(self.employee, id)
					.then(
						function (response){
							console.log('Employee updated successfully');
							self.successMessage='Employee updated successfully';
							self.errorMessage='';
							self.done = true;
							$scope.form.myEmployeeForm.$setPristine();
						},
						function(errResponse){
							console.error('Error while updating Employee');
							self.errorMessage='Error while updating Employee: '+errResponse.data;
							self.successMessage='';
						}
					);
			}
			else{
				self.errorMessage = 'Identical CNP already exists.';
				self.successMessage = '';
			}

					
                },
                function (errResponse) {
                    console.error('Error while loading employee ' + id + ', Error : ' + errResponse.data);
					self.errorMessage='No such employee exists to update.';
					self.successMessage='';
                }
            );
		
		}
		
		function deposit(accountNo){
			if(self.sumToTransfer !== null && self.sumToTransfer !== undefined){
				var depositedSum = self.sumToTransfer;
			}
			else{
				var depositedSum = self.depositSum[accountNo];
			}
			console.log('About to deposit '+depositedSum+' to account '+accountNo);
			
			UserService.depositSum(accountNo, depositedSum)
			.then(
				function(){
					console.log('Sum deposited successfully.');
					self.successMessage='Sum deposited successfully.';
					self.errorMessage='';
				},
				function(errResponse){
					console.error('Error while depositing sum in account: '+accountNo+', Error: '+errResponse.data);
				}
			);
		}
		
		function withdraw(accountNo){
			if(self.sumToTransfer !== null && self.sumToTransfer !== undefined){
				var withdrawnSum = self.sumToTransfer;
			}
			else{
				var withdrawnSum = self.withdrawnSum[accountNo];
			}
			console.log('About to withdraw '+withdrawnSum+' from account '+accountNo);
			
			UserService.withdrawSum(accountNo, withdrawnSum)
			.then(
				function(){
					console.log('Sum withdrawn successfully.');
					self.successMessage='Sum withdrawn successfully.';
					self.errorMessage='';
				},
				function(errResponse){
					console.error('Error while withdrawing sum from account: '+accountNo+', Error: '+errResponse.data);
				}
			);
		}
		
		function transfer(){
			console.log('About to transfer from account '+self.fromAccountNo+' to account '+self.toAccountNo);
			withdraw(self.fromAccountNo);
			deposit(self.toAccountNo);
			console.log('Transfer succeeded.');
			self.successMessage='Transfer succeeded.';
			self.errorMessage='';
			self.sumToTransfer = 0;
		}

        function removeUser(id){
            console.log('About to remove User with id '+id);
            UserService.removeUser(id)
                .then(
                    function(){
                        console.log('User '+id + ' removed successfully');
						self.successMessage = 'User deleted successfully';
						self.errorMessage='';
                    },
                    function(errResponse){
                        console.error('Error while removing user '+id +', Error :'+errResponse.data);
                    }
                );
        }
		function deleteEmployee(id) {
		    console.log('About to remove Employee with id '+id);
            UserService.removeEmployee(id)
                .then(
                    function(response){
                        console.log('Employee '+id + ' removed successfully');
						console.log(response);
						self.successMessage = 'Employee deleted successfully';
						self.errorMessage='';
                    },
                    function(errResponse){
                        console.error('Error while removing employee '+id +', Error :'+errResponse.data);
                    }
                );
		}
		function removeAccount(accountId){
			console.log('About to remove Account no '+accountId);
			UserService.removeAccount(accountId)
				.then(
					function(){
						console.log('Account '+accountId+' removed successfully.');
						self.successMessage='Account removed successfully.';
						self.errorMessage='';
					},
					function(errResponse){
						console.error('Error while removing account '+accountId+', Error: '+errResponse.data);
					}
				);
		}

        function getAllUsers(){
            return UserService.getAllUsers();
        }

		function getAllEmployees() {
			return UserService.getAllEmployees();
		}
		
		function login(){
			var username = self.loginAccountUsername;
			console.log('About to login user: '+username);
		
			UserService.getLoginAccount(username).then(
				function (loginAccount) {
					console.log('Login Account fetched successfully for user '+username);
					//self.loginAccount = loginAccount;
									
					UserService.login(loginAccount, self.loginAccountPassword)
						.then(
							function(response){
								console.log('Logged in successfully');
								self.done=true;
								self.displayLogin = false;
								if(username==='employee')
									self.displayEmployeeView = true;
								if(username==='admin')
									self.displayAdminView = true;
							},
							function(errResponse){
								console.error('Error while logging in');
								self.errorMessage = 'Invalid username and/or password.';
								self.successMessage = '';
							}
						);
					
				},
				function (errResponse) {
					console.error('Error while fetching Login Account for user '+username+', Error : '+errResponse.data);
				}
			);
			console.log(self.loginAccount);
			
			
			
		}
		
		
        function editUser(id) {
            self.successMessage='';
            self.errorMessage='';
            UserService.getUser(id).then(
                function (user) {
                    self.user = user;
					self.userToUpdate = user;
                },
                function (errResponse) {
                    console.error('Error while loading user ' + id + ', Error : ' + errResponse.data);
                }
            );
        }
		
		function editEmployee(id){
			self.successMessage='';
            self.errorMessage='';
			UserService.getEmployee(id).then(
				function(employee){
					self.employee = employee;
					console.log('self.employee');
					console.log(self.employee);
				},
				function(errResponse){
					console.error('Error while loading employee '+id+',Error: ' + errResponse.data);
				}
			);
				
		}
		
		function editAccounts(id){
			self.displayAccounts = true;
			self.displayClients = false;
			self.successMessage='';
			self.errorMessage='';
			
			UserService.getUser(id).then(
                function (user) {
					console.log(user);
                    self.userWithAccount = user;
                },
                function (errResponse) {
                    console.error('Error while loading user ' + id + ', Error : ' + errResponse.data);
                }
            );
			
			UserService.getUserAccounts(id).then(
				function (accounts) {
					self.accounts = accounts;
				},
				function (errResponse) {
					console.error('Error while loading accounts for user with id: '+ id + ', Error: '+ errResponse.data);
				}
			);
		}
		
		function doneWithAccounts(){
			self.displayAccounts=false;
			self.displayClients=true;
		}
		
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.user={};
            $scope.myForm.$setPristine(); //reset Form
        }
		
		function employeeReset(){
			self.successMessage='';
            self.errorMessage='';
            self.employee={};
            $scope.form.myEmployeeForm.$setPristine(); //reset Form
		}
    }


    ]);