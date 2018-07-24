<%@include file="../shared/flows-header.jsp" %>
<div class="container">
	
	<div class="row">
	
		<div class="col-sm-6">
	
			<div class="panel panel-primary">
				
				<div class="panel-heading">
			
					<h4><font color="white">Personal Details</font></h4>
				</div>
			
				<div class="panel-body">
					<div class="text-center">
						<p><font color="green">Name : ${registerModel.user.firstName} ${registerModel.user.lastName}</font></p>
						<p>Email : ${registerModel.user.email}</p>
						<p>Contact : ${registerModel.user.contactNumber}</p>
						<p>Role : ${registerModel.user.role}</p>
						<p>
							<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
						</p>
					</div>
				</div>
			
			</div>
					
		
		</div>
		
		<div class="col-sm-6">
		
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4><font color="white">Billing Address</font></h4>
				</div>
			
				<div class="panel-body">
					<div class="text-center">
						<p>${registerModel.billing.addressLineOne}, </p>
						<p>${registerModel.billing.addressLineTwo}, </p>
						<p>${registerModel.billing.city} -  ${registerModel.billing.postalCode}, </p>
						<p>${registerModel.billing.state}</p>
						<p>${registerModel.billing.country}</p>
						<p>
							<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
						</p>
						
					</div>
					
				</div>
				
			
			</div>
			
		
		</div>
		
	
	</div>
	
	
	<div class="row">
		
		<div class="col-sm-4 col-sm-offset-4">
			
			<div class="text-center">
				
				<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-lg btn-primary">Confirm</a>
				
			</div>
			
		</div>
		
	</div>

</div>
<%@include file="../shared/flows-footer.jsp" %>