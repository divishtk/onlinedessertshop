$(function()
		{
	// solving the active menu problem
		switch(menu){
		
		case 'Home':
			$('#home').addClass('active');
			break;
		case 'About Us':
			$('#about').addClass('active');
			break;
		case 'Contact Us':
			$('#contact').addClass('active');
			break;
			
		case 'All Products':
			$('#listProducts').addClass('active');
			break;
		case 'Manage Products':
			$('#manageProducts').addClass('active');
			break;
		case 'Shopping Cart':
			$('#userCart').addClass('active');
			break;
			default:
				if(menu == "Home") 
					break;
		case 'Home':
			$('#listProducts').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
			
			
			
			}
		
		//to tackle csrf token
		var token = $('meta[name="_csrf"]').attr('content');
		var header = $('meta[name="_csrf_header"]').attr('content');
		
		if (token.length > 0 && header.length > 0) {		
			// set the token header for the ajax request
			$(document).ajaxSend(function(e, xhr, options) {			
				xhr.setRequestHeader(header,token);			
			});				
		}
		
		
		
		//code for jquery dataTable

		var $table = $('#productListTable');
		
		
		//execute  below code only where we have this table
		
		if($table.length)
			{
			
			
			//console.log("Inside the table");
			
			var jsonUrl='';
			if(window.categoryId =='')
			{
				
				
				jsonUrl=window.contextRoot +'/json/data/all/products';
			}
			else
				{
				
				jsonUrl=window.contextRoot +'/json/data/category/'+ window.categoryId +'/products';
				}
			
			
			$table.DataTable({
				
				lengthMenu:[  [3,5,10,-1],  ['3 Records','5 Records','10 Records','ALL'] ],
				pageLength: 5,
				ajax: {
					
					url:jsonUrl,
					dataSrc:''
					
				},
				columns:[
					
					
						{
							
							data:'code',
							mRender: function(data, type, row)
							{
								
								return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
								
							}
							
						},
					
					{
						data:'name'
						
					},

					{
						data:'brand'
						
					},

					{
						data:'unitPrice',
							mRender : function(data,type,row)
							{
								return '&#8377; ' + data
								
							}
						
					},

					{
						data:'quantity',
							mRender :function(data,type,row)
							{
								
								if(data <1)
									{
									
									return'<span style="color:red">Out Of Stock! </span>';
									
									}
								return data;
								
							}
						
					},
					
					{
						data:'id',
						bSortable:false,
						mRender: function(data,type,row)
						{
							var str = '';
							str += '<a href="'+ window.contextRoot+ '/show/'+ data+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
							
							
							
							if(userRole == 'SUPPLIER')
								{
								str += '<a href="'+ window.contextRoot+ '/manage/'+ data+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span> </a>';

								}
							else
								{
	
									if(row.quantity <1)
										{
								
											str +=  '<a href="javascript void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span> </a>'	;
			
										}
									else
									  {
			
									   str += '<a href="'+ window.contextRoot+ '/cart/add/' +data+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span> </a>';
									   }
								
								
								}
						
							
							return str;
						}
						
						
					}
					
						]
				
			
				
				
			});
			
			
			
			
			
			}
		
		
		
		//dissmising alert after 3 seconds
		
		/* for fading out the alert message after 3 seconds */
		$alert = $('.alert');
		if($alert.length) 
		{
			setTimeout(function() 
					{
		    	$alert.fadeOut('slow');
			   }, 3000);		
		}
		
		
		
		//=========================Toogle Box Of Product Activate and Deactivate====================
		$('.switch input[type="checkbox"]').on('change',function()
				{
				var checkbox=$(this);
				
				var checked=checkbox.prop('checked');
				var dMsg=(checked)? 'You want to activate product?':
									'You wanna deactivate?';
				
				var value=checkbox.prop('value');
				
				bootbox.confirm({size:'medium',
								 title:'Product Activation & Deactivation',
								 message:dMsg,
								 callback:function(confirmed)
								 {
									 if(confirmed)
										 {
										 
										 console.log(value);
										 bootbox.alert({size:'medium',title:'Information',message:'You are going to perform operation on product' +value });
										 
										 }
									 
									 else
										 {
										 
										 checkbox.prop('checked',!checked);
										 
										 
										 }
									 
								 }
					
				
				
				
				
				
				});
				
			
			
				});
		
		
		
		
		
		
		//DataTable For Admin
		
		var $adminProductsTable = $('#adminProductsTable');
		
		
		//execute  below code only where we have this table
		
		if($adminProductsTable.length)
			{
			
			
			//console.log("Inside the table");
			
			var jsonUrl=window.contextRoot +'/json/data/admin/all/products';
	
			$adminProductsTable.DataTable({
				
				lengthMenu:[  [10,30,50,-1],  ['10 Records','30 Records','50 Records','ALL'] ],
				pageLength: 30,
				ajax: {
					
					url:jsonUrl,
					dataSrc:''
					
				},
				columns:[
					{
						data:'id'
						
						
					},
					
					
						{
							
							data:'code',
							mRender: function(data, type, row)
							{
								
								return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>';
								
							}
							
						},
					
					{
						data:'name'
						
					},

					{
						data:'brand'
						
					},

				

					{
						data:'quantity',
							mRender :function(data,type,row)
							{
								
								if(data <1)
									{
									
									return'<span style="color:red">Out Of Stock! </span>';
									
									}
								return data;
								
							}

					},
					
					{
						data:'unitPrice',
							mRender : function(data,type,row)
							{
								return '&#8377; ' + data
								
							}
						
					},
					
					{
						data:'active',
						bSortable:false,
						mRender : function(data,type,row)
						{
							
							var str="";
							str+='<label class="switch">';
							if(data)
								{
								
								str+='<input type="checkbox" checked="checked" value="'+row.id+'" />';
								
								}
							else
								{
								
								
								str+='<input type="checkbox" value="'+row.id+'" />';
								}
							
						
							str+='<div class="slider"></div></label>'
							
							return str;
						}
							
					
						
						
						
					},
					
					{
						
						data:'id',
						bSortable:false,
						mRender :function(data,type,row)
						{
							
							var str='';
							
							str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
						str+='<span class="glyphicon glyphicon-pencil"></span></a>';
						
						
						return str;
							
						}
						
						
					}
					
					
					
						],
						
						initComplete:function()
						{
							
							var api=this.api();
							api.$('.switch input[type="checkbox"]').on('change',function()
									{
								var checkbox=$(this);
								
								var checked=checkbox.prop('checked');
								var dMsg=(checked)? 'You want to activate product?':
													'You wanna deactivate?';
								
								var value=checkbox.prop('value');
								
								bootbox.confirm({size:'medium',
												 title:'Product Activation & Deactivation',
												 message:dMsg,
												 callback:function(confirmed)
												 {
													 if(confirmed)
														 {
														 
														 
														 var activationUrl=window.contextRoot+'/manage/product/' + value +'/activation';
														 
														 $.post(activationUrl,function(data)
																 
																 {
															 
															 
															 
															 bootbox.alert({size:'medium',title:'Information', message:data  });
																
															 
															
																 })
												
														 
														 
														 
														 
														 
														 
														 
														 console.log(value);
														 bootbox.alert({size:'medium',title:'Information',message:'You are going to perform operation on product' +value });
														 
														 }
													 
													 else
														 {
														 
														 checkbox.prop('checked',!checked);
														 
														 
														 }
													 
												 }
									
								
								
								
								
								
								});
								
							
							
								});
						
							
						}
				
			
				
				
			});

			
			}
		
		
		
		
			//-------------------- validation code for category
		
		var $categoryForm=$('#categoryForm');
		if($categoryForm.length) {
			
			$categoryForm.validate({			
					rules: {
						name: {
							required: true,
							minlength: 3
						},
						description: {
							required: true,
							minlength: 3					
						}				
					},
					messages: {					
						name: {
							required: 'Please enter category name!',
							minlength: 'Please enter atleast 2 characters'
						},
						description: {
							required: 'Please enter some description!',
							minlength: 'Please enter atleast 3 characters'
						}					
					},
					errorElement : "em",
					errorPlacement : function(error, element)
					
					{
						//add class of help-block
						error.addClass('help-block');
						
						//add the error element after input element
						error.insertAfter(element);
					}				
				}
			
			);
			
		}
		
		
		
	//-------------------- validation code for login form
		
		var $loginForm=$('#loginForm');
		if($loginForm.length) {
			
			$loginForm.validate({			
					rules: {
						username: {
							required: true,
							email: true
						},
						password: {
							required: true,
											
						}				
					},
					messages: {					
						username: {
							required: 'Please enter username!',
							email: 'Please enter valid email address'
						},
						password: {
							required: 'Whoa enter correct one!'	
						}					
					},
					errorElement : "em",
					errorPlacement : function(error, element)
					
					{
						//add class of help-block
						error.addClass('help-block');
						
						//add the error element after input element
						error.insertAfter(element);
					}				
				}
			
			);
			
		}
		
		
		
		//------------------------
		//handling click event of refresh cart
		
		$('button[name="refreshCart"]').click(function()

				{
			//fetch cartLine id
			var cartLineId = $(this).attr('value');
			var countElement = $('#count_' + cartLineId);
			
			
			var originalCount = countElement.attr('value');
			var currentCount =countElement.val();
			
			// do the checking only the count has changed
			if(currentCount !== originalCount) 
			
			{			
				
				if(currentCount < 1 || currentCount > 3) 
				
				{
					//reversing back to original count 
					// set the field back to the original field
					countElement.val(originalCount);
					bootbox.alert({
						size: 'medium',
				    	title: 'Error',
				    	message: 'Product  should be minimum 1 and maximum 3!'
					});
	
				}
				
				else
				{
				var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;
				//forward it to controler
				window.location.href = updateUrl;
			
			
				}
			
				
			}
				
			
			
				})
				
				
				
		
		
		
		
			});
	