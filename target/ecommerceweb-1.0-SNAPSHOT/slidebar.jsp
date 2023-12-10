
<section>

    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <h2>Category</h2>
                    <div class="panel-group category-products" id="accordian">

                        <myCate:CartCategory/>
                    </div>
                    <form action="SearchForProduct" method="get">
                        <div class="price-range">
                            <h2>Price Range</h2>
                            <div class="well text-center">
                                <div class="form-group">
                                    <label for="minPrice">Min Price:</label>
                                    <input type="number" class="form-control" id="minPrice" name="down" placeholder="Enter min price" required />
                                </div>
                                <div class="form-group">
                                    <label for="maxPrice">Max Price:</label>
                                    <input type="number" class="form-control" id="maxPrice" name="up" placeholder="Enter max price" required />
                                </div>
                                <input type="submit" class="btn btn-default">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
