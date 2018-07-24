<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">


	<div class="row">



		<c:if test="${not empty message}">

			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}

				</div>
			</div>

		</c:if>




		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>
						<font color="white">Product Management</font>
					</h4>

				</div>

				<div class="panel-body">
					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">

						<div class="form-group">
							<label class="control-label col-md-4">Enter Product Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" class="form-control"
									placeholder="Product Name" />
								<sf:errors path="name" cssClass="help-block" element="em" />

								<!-- <em class="help-block">Please
								Enter Product Name!</em>
 -->
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4">Enter Brand Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									class="form-control" placeholder="Brand Name" />
								<!-- <em class="help-block">Please
								Enter Brand Name!</em> -->
								<sf:errors path="brand" cssClass="help-block" element="em" />

							</div>
						</div>






						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product
								Description</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4"
									class="form-control" placeholder="Enter your description here!" />
								<sf:errors path="description" cssClass="help-block" element="em" />

							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Enter
								Unit Price :</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice"
									class="form-control" placeholder="Enter Unit Price " />
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />
								<!-- <em class="help-block">Please
								Enter Unit Price</em>
							 -->
							</div>
						</div>




						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity
								Available :</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity"
									class="form-control" placeholder="Quantity Available" />
								<!-- <em class="help-block">Please
								Enter Quantity!</em> -->

							</div>
						</div>


						<!--  File element for image -->

						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Select
								an Image :</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />

								<!-- <em class="help-block">Please
								Enter Quantity!</em> -->

							</div>
						</div>



						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select
								Category :</label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId"
									path="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />


								<c:if test="${product.id==0 }">


									<div class="text-right">

										</br>
										<button type="button" data-toggle="modal"
											data-target="#myCategoryModal" class="btn btn-warning sm">Add
											Category</button>
									</div>




								</c:if>



							</div>
						</div>


						<div class="form-group">

							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" value="Submit"
									class="btn btn-primary" />



								<!-- Hidden Fields -->

								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />
								<sf:hidden path="active" />
							</div>
						</div>

					</sf:form>


				</div>


			</div>

		</div>
	</div>


	<hr />
	<h1>
		<font color="#33FFD4">Available Products</font>
	</h1>
	<hr />

	<div class="row">


		<div class='col-xs-12'>


			<table id="adminProductsTable"
				class="table table-condensed table-bordered">

				<thead>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>

						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>Activate</th>
						<th>Edit</th>
					</tr>
				</thead>

				<%-- <tbody>

					<tr>
						<td>1</td>
						<td><img class="adminDataTableImg"
							src="${contextRoot}/resources/images/PRDABC123DEFX.jpg"
							alt="Choclate Cake"></td>
						<td>Choclate Cake</td>
						<td>Monginis<td>3
						</td>
						<td>&#8377; 600.00/-</td>
						<td>
						<label class="switch">
						
						<input type="checkbox" checked="checked" value="1" />
						<div class="slider"></div>
						</label>
						</td>
						<td>
						<a href="${contextRoot}/manage/1/product" class="btn btn-warning">
						<span class="glyphicon glyphicon-pencil"></span>
						</a>
						</td>
					</tr>
					
					
					
					<tr>
						<td>1</td>
						<td><img class="adminDataTableImg"
							src="${contextRoot}/resources/images/PRDABC123DEFX.jpg"
							alt="Choclate Cake"></td>
						<td>Choclate Cake</td>
						<td>Birdiys</td>
						<td>3</td>
						<td>&#8377; 600.00/-</td>
						<td>
						<label class="switch">
						
						<input type="checkbox" value="1" />
						<div class="slider"></div>
						</label>
						</td>
						<td>
						<a href="${contextRoot}/manage/1/product" class="btn btn-warning">
						<span class="glyphicon glyphicon-pencil"></span>
						</a>
						</td>
					</tr>
				</tbody>


 --%>





				<tfoot>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>
						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>Activate</th>
						<th>Edit</th>
					</tr>
				</tfoot>


			</table>


		</div>


	</div>




<!-- Adding a MODAL DIALOOG BOX FOR ADDINF CATEGORYY -->

	<div class="modal fade" id="myCategoryModal" role="dialog"
		tabindex="-1">

		<div class="modal-dialog" role="document">

			<div class="modal-content">


				<div class="modal-header">

					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>

					<h4 class="modal-title">Add New Category</h4>

				</div>
				<div class="modal-body">

					<!-- Category Form -->
					<sf:form id="categoryForm" class="form-horizontal"
						modelAttribute="category" action="${contextRoot}/manage/category"
						method="POST">

						<div class="form-group">
							<label for="category_name" class="control-label col-md-4">Category
								Name</label>

						<div class="col-md-8 validate">
							<sf:input type="text" path="name" id="category_name"
								class="form-control" placeholder="Category Name" />
						</div>
						</div>


						<div class="form-group">
							<label for="description" class="control-label col-md-4">Category
								Description</label>


							<div class="col-md-8 validate">
								<sf:textarea type="text" cols="" rows="5" path="description"
									id="category_description" class="form-control"
									placeholder="Category Descrption" />
							</div>
						</div>
						



							<div class="form-group">


							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category" class="btn btn-primary" />

							</div>
						</div>


					</sf:form>


				</div>




			</div>


		</div>






	</div>





</div>
