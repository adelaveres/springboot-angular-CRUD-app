<nav class="navbar navbar-default">
		<div class = "container-fluid">
			<div class = "navbag-header">
				<a class = "navbar-brand">MyBanking</a>
			</div>
			<ul class = "nav navbar-nav">
			<li><button class="btn btn-link" ng-click="ctrl.displayLoginPage()">Login</button></li>
			<li ng-if="ctrl.displayEmployeeView"><button class="btn btn-link" ng-click="ctrl.displayEmployeePage()" >Clients</button></li>
			<li ng-if="ctrl.displayAdminView"><button class="btn btn-link" ng-click="ctrl.displayAdminPage()">Employees</button></li>
			</ul>
		</div>
	</nav>

	<!--    -----------------------------------------    Login    -------------------------------------------   -->
	
	<div class="generic-container" style="text-align:center; margin: 5% 30%; max-width:40%;" ng-if="ctrl.displayLogin">
		<div class="panel panel-default">
			<div class="panel-heading"><span class="lead">Login</span></div>
			<div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	        <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
			
			<div class="panel-body">
				<div class="formcontainer">
					<form ng-submit="ctrl.login()" name="loginForm" class="form-horizontal">
						<input type="hidden" ng-model="ctrl.loginAccount.loginId" />
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="username">Username</label>
								<div class="col-md-7">
									<input type="text" ng-model="ctrl.loginAccountUsername" id="username" class="username form-control input-sm" placeholder="username" required ng-minlength="3"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="pass">Password</label>
								<div class="col-md-7">
									<input type="password" ng-model="ctrl.loginAccountPassword" id="pass" class="form-control input-sm" placeholder="password" required ng-minlength="3"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="form-actions" style="text-align:center">
								<input type="submit" value="Login" class="btn btn-primary" ng-disabled="loginForm.$invalid || loginForm.$pristine"/>
							</div>
						</div>
						
						
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	<!--    -----------------------------------------    Employee View    -------------------------------------------   -->
	
<div class="generic-container" ng-if="ctrl.displayEmployeeView">
	
    <div class="panel panel-default" ng-if="ctrl.displayClients">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Clients </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.user.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.name" id="uname" class="username form-control input-sm" placeholder="Client Name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="cnp">Personal Numerical Code</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.cnp" id="cnp" class="form-control input-sm" placeholder="Client Personal Numerical Code" required ng-pattern="ctrl.onlyIntegers" ng-minlength="13"/>
	                        </div>
	                    </div>
	                </div>
	
                  <div class="row">
                    <div class="form-group col-md-12">
                      <label class="col-md-2 control-lable" for = "idnumber">Identity Card Number</label>
                      <div class="col-md-7">
                        <input type="text" ng-model = "ctrl.user.idnumber" id = "idnumber" class="form-control input-sm" placeholder="Client ID Card Number" required ng-pattern="ctrl.onlyIntegers"/>
                      </div>
                    </div>
                  </div>
                  
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="address">Address</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.address" id="address" class="form-control input-sm" placeholder="Client Address" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit" value="Add" ng-click="ctrl.addingUser = true" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine"/>
							<input type="submit" value="Update" ng-click="ctrl.updatingUser = true" class="btn btn-success btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine"/>
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
  
	
	<div class="panel panel-default" ng-if="ctrl.displayAccounts">
		<!-- Client Accounts panel -->
		<div class="panel-heading"><span class="lead">Accounts</span></div>
		<div class="panel-body">
				<div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
			<h4>{{ctrl.userWithAccount.name}}</h4>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
					<tr>
						<th>No</th>
						<th>Type</th>
						<th>Sum</th>
						<th>Creation Date</th>
						<th width="100"></th>
		                <th width="100"></th>
						<th width="100"></th>
					</tr>
					</thead>
					<tbody>
					<tr ng-repeat="account in ctrl.accounts">
						<td>{{account.accountId}}</td>
						<td>{{account.type}}</td>
						<td>{{account.sumMoney | currency}}</td>
						<td>{{account.creationDate | date}}</td>
						<td>
							<button ng-click="ctrl.deposit(account.accountId)" class="btn custom-width">Deposit</button>
							<input type="text" ng-model="ctrl.depositSum[account.accountId]" class="input-sm" placeholder="$0.00" required ng-pattern="ctrl.onlyIntegers"/>
						</td>
						
						<td>
							<button ng-click="ctrl.withdraw(account.accountId)" class="btn custom-width">Withdraw</button>
							<input type="text" ng-model="ctrl.withdrawnSum[account.accountId]" class="input-sm" placeholder="$0.00" required ng-pattern="ctrl.onlyIntegers"/>
						</td>
						
						<td> 
							<button ng-click="ctrl.removeAccount(account.accountId)" class="btn btn-danger btn-sm custom-width">Delete</button>
						</td>
					</tr>
					</tbody>
				</table>
			</div>	
			
			<button class="floatRight" ng-click="ctrl.editAccounts(ctrl.userWithAccount.id)"><a href="#">Refresh</a></button>
			
			<div style="text-align:center" >
				<button class="btn btn-success custom-width" ng-click="ctrl.transfer()" >Transfer</button>
			</div>
 
            
			<div class="row" style="text-align:center" >
				<div class="col col-md-6">
					<div class="row">
						<label id="from_account">From:</label>
						<input type="text" ng-model="ctrl.fromAccountNo" id="from_account" placeholder="account(no.):"/>
					</div>
				</div>
				
				<div class="col col-md-6">
					<div class="row">
						<label id="to_account">To:</label>
						<input type="text" ng-model="ctrl.toAccountNo" id="to_account" placeholder="account(no.):"/>
					</div>
				</div>
				
				<input type="text" ng-model="ctrl.sumToTransfer" placeholder="$0.00">
			</div>

			
			<div class="row floatRight">
				<button class="btn btn-sm" ng-click="ctrl.doneWithAccounts()"><a href=#>Done</a></button>
			</div>
			
			
			<!-- Create New Account -->
			
			<div class="row" style="float:left; margin-left:20px;">
				<button type="button" class="btn btn-info" ng-click="ctrl.toggleDisplayCreateAccount()">Add Account</button>
			</div>
		
		<div class="generic-container" ng-if="ctrl.displayCreateAccount">
			<div  class="row panel panel-default">
				<div class="panel-body">
					
					<div class="row">
						<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="type">Type</label>
							<div class="col-md-7">
								<select ng-model="ctrl.account.type" id="type" ng-options="type for type in ctrl.accountTypes">
								</select>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="sum">Initial sum</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.account.sumMoney" id="sum" class="form-control input-sm" placeholder="$0.00" required ng-pattern="ctrl.onlyIntegers" ng-minlength="2"/>
							</div>
						</div>
					</div>
					
					<div class="row" style="float:left; margin-left:20px;">
						<div class="form-actions" >
							<button class="btn btn-warning" ng-click="ctrl.addAccount()">Create</button>
						</div>
					</div>
				
				</div>
			</div>		
		</div>		

			
			
		</div>
	</div>

	
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Clients </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>PNC</th>
		                <th>NAME</th>
		                <th>ID NUMBER</th>
		                <th>ADDRESS</th>
		                <th width="100"></th>
		                <th width="100"></th>
                        <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="client in ctrl.getAllUsers()">
		                <td>{{client.cnp}}</td>
		                <td>{{client.name}}</td>
		                <td>{{client.idnumber}}</td>
		                <td>{{client.address}}</td>
		                <td><button type="button" ng-click="ctrl.editUser(client.id)" class="btn btn-info custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeUser(client.id)" class="btn btn-danger custom-width">Delete</button></td>
						<td><button type="button" ng-click="ctrl.editAccounts(client.id)" class="btn btn-warning custom-width">Accounts</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
	
</div>

























<!--    -----------------------------------------    Admin View    -------------------------------------------   -->
	
<div class="generic-container" ng-if="ctrl.displayAdminView">
	
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Employees </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submitEmployee()" name="form.myEmployeeForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.employee.employeeId" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="ename">Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.employee.name" id="ename" class="username form-control input-sm" placeholder="Employee Name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="ecnp">Personal Numerical Code</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.employee.cnp" id="ecnp" class="form-control input-sm" placeholder="Employee Personal Numerical Code" required ng-pattern="ctrl.onlyIntegers" ng-minlength="13"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="eaddress">Address</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.employee.address" id="eaddress" class="form-control input-sm" placeholder="Employee Address" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit" value="Add" ng-click="ctrl.addingEmployee = true" class="btn btn-primary btn-sm" ng-disabled="form.myEmployeeForm.$invalid || form.myEmployeeForm.$pristine"/>
							<input type="submit" value="Update" ng-click="ctrl.updatingEmployee = true" class="btn btn-success btn-sm" ng-disabled="form.myEmployeeForm.$invalid || form.myEmployeeForm.$pristine"/>
	                        <button type="button" ng-click="ctrl.employeeReset()" class="btn btn-warning btn-sm" ng-disabled="form.myEmployeeForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
  
	
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Employees </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>PNC</th>
		                <th>NAME</th>
		                <th>ADDRESS</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="e in ctrl.getAllEmployees()">
		                <td>{{e.cnp}}</td>
		                <td>{{e.name}}</td>
		                <td>{{e.address}}</td>
		                <td><button type="button" ng-click="ctrl.editEmployee(e.employeeId)" class="btn btn-info custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.deleteEmployee(e.employeeId)" class="btn btn-danger custom-width">Delete</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
	
</div>