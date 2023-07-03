package com.techgiants.topiefor.process;

import com.techgiants.topiefor.dao.impl.CategoryDaoImpl;
import com.techgiants.topiefor.dao.impl.IngredientDaoImpl;
import com.techgiants.topiefor.dao.impl.Ingredient_OrderDaoImpl;
import com.techgiants.topiefor.dao.impl.OrderDaoImpl;
import com.techgiants.topiefor.dao.impl.ProductDaoImpl;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Category;
import com.techgiants.topiefor.model.Ingredient;
import com.techgiants.topiefor.model.Ingredient_Order;
import com.techgiants.topiefor.model.Ingredient_OrderItem;
import com.techgiants.topiefor.model.Order;
import com.techgiants.topiefor.model.Product;
import com.techgiants.topiefor.model.QuantityUnit;
import com.techgiants.topiefor.model.Recipe;
import com.techgiants.topiefor.model.Unit;
import com.techgiants.topiefor.model.User;
import com.techgiants.topiefor.service.Ingredient_OrderService;
import com.techgiants.topiefor.service.impl.CategoryServiceImpl;
import com.techgiants.topiefor.service.impl.IngredientServiceImpl;
import com.techgiants.topiefor.service.impl.Ingredient_OrderServiceImpl;
import com.techgiants.topiefor.service.impl.OrderServiceImpl;
import com.techgiants.topiefor.service.impl.ProductServiceImpl;
import com.techgiants.topiefor.status.Status;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author STUDIO 18
 */
public class AdminRequest extends ProcessRequest {

    private IngredientServiceImpl ingredOrderServ;

    protected Ingredient_OrderService ingreOrderServ;

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        super.processTheRequest(request, response);

        User user = (User) request.getSession().getAttribute("user");

        if (process == null || process.isEmpty()) {
            return;
        }

        if (user == null || !user.getRole().getRole().equalsIgnoreCase("admin")) {
            msg = "Please Login As First";
            viewPage = "login.jsp";
            return;
        }

        switch (process) {
            case "add-category":
                createCategory(request, response);
                break;
            case "update-category":
                editCategory(request, response);
                break;
            case "deactivate-category": {
                try {
                    deactivateCategory(request, response);
                } catch (ArgumentException ex) {
                    Logger.getLogger(AdminRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "delivery-note":
                downloadDeliveryNote(request,response);
                
                break;
            case "activate-category":
                activateCategory(request, response);
                break;
            case "add-ingredient":
                createIngredient(request, response);
                break;
            case "update-ingredient":
                editIngredient(request, response);
                break;
            case "activate-ingredient":
                activateIngredient(request, response);
                break;
            case "add-product":
                createProduct(request, response);
                break;
            case "all-products":
                getAllProducts(request, response);
                break;
            case "adding-product":
                addAllRequiredResourceForAddingProduct(request, response);
                break;
            case "update-product":
                editProduct(request, response);
                break;
            case "activate-product":
                activateProduct(request, response);
                break;
            case "all-categories":
                returnAllTheCategories(request, response);
                break;
            case "all-ingredients":
                returnAlltheIngredients(request, response);
                break;
            case "all-ingredient-orders":
                returnAlltheIngredientsOrders(request, response);
                break;
            case "all-orders":
                returnAlltheOrders(request, response);
                break;
            case "ordering-ingredient":
                addAllRequiredResourceForOrderingIngredients(request, response);
                break;
            case "confirm-order":
                confirmOrder(request, response);
                break;
            case "order-ingredient":
                createIngredientsOrder(request, response);
                break;
            case "order-product":
                createProductOrder(request, response);
                break;
            case "update-order-status":
                updateOrderStatus(request, response);
                break;

        }

    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response) {

        catServ = CategoryServiceImpl.getInstance(CategoryDaoImpl.getInstance(dbm.getConnection()));

        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        Category cat = new Category();

        try {
            cat.setDescription(desc);
            cat.setName(name);

            if (catServ.addCategory(cat)) {

                msg = "Category Successfully Added";

            } else {
                msg = "Could not Add Category";
            }

        } catch (ArgumentException ex) {
            msg = "Error: " + ex.getMessage();
        }

        viewPage = "category.jsp";
        request.setAttribute("categories", catServ.getAllCategories());

    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response) {
        catServ = CategoryServiceImpl.getInstance(CategoryDaoImpl.getInstance(dbm.getConnection()));
        String categoryId = request.getParameter("id");
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");

        try {
            Category cat = new Category(name, Integer.parseInt(categoryId), desc, true);
            cat.setCategoryId(Integer.parseInt(categoryId));
            cat.setName(name);
            cat.setDescription(desc);
            cat.setIsActive(true);

            // Make sure catServ is not null before calling its methods
            if (catServ != null && catServ.updateCategory(cat)) {
                msg = "Category Successfully Updated";
            } else {
                msg = "Could not update Category";
            }
        } catch (ArgumentException ex) {
            msg = "Error: " + ex.getMessage();
        }
        viewPage = "category.jsp";
        request.setAttribute("categories", catServ.getAllCategories());
    }

    private void deactivateCategory(HttpServletRequest request, HttpServletResponse response) throws ArgumentException {
        catServ = CategoryServiceImpl.getInstance(CategoryDaoImpl.getInstance(dbm.getConnection()));

        request.setAttribute("categories", catServ.getAllCategories());

        String categoryId = request.getParameter("categoryId");
        viewPage = "category.jsp";
        int id;

        try {
            id = Integer.parseInt(categoryId);

        } catch (NumberFormatException numberFormatException) {
            msg = "Invalid category Id ";

            request.setAttribute("categories", catServ.getAllCategories());
            return;

        }

        if (catServ.deleteCategory(id)) {
            msg = "category de-activated";
        } else {
            msg = "failed to de-activate category";
        }

        request.setAttribute("categories", catServ.getAllCategories());

    }

    private void activateCategory(HttpServletRequest request, HttpServletResponse response) {
        catServ = CategoryServiceImpl.getInstance(CategoryDaoImpl.getInstance(dbm.getConnection()));

        request.setAttribute("categories", catServ.getAllCategories());

        String categoryId = request.getParameter("categoryId");
        viewPage = "category.jsp";
        int id;

        try {
            id = Integer.parseInt(categoryId);

        } catch (NumberFormatException numberFormatException) {
            msg = "Invalid category Id ";

            request.setAttribute("categories", catServ.getAllCategories());
            return;

        }

        try {
            if (catServ.activateCategory(id)) {
                msg = "category de-activated";
            } else {
                msg = "failed to de-activate category";
            }
        } catch (ArgumentException ex) {
            System.out.println("Err: " + ex.getMessage());;
        }

        request.setAttribute("categories", catServ.getAllCategories());

    }

    private void createIngredient(HttpServletRequest request, HttpServletResponse response) {
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl
                .getInstance(dbm.getConnection()));

        String name = request.getParameter("ingred-name");
        String minStock = request.getParameter("min-stock");
        String unitStr = request.getParameter("unit");
        double onStock = 0;

        Ingredient ingre = new Ingredient();

        try {
            ingre.setIsActive(true);
            ingre.setMinStockOnHand(Double.parseDouble(minStock));
            ingre.setUnit(Unit.values()[Integer.parseInt(unitStr)]);
            ingre.setName(name);
            ingre.setStock(onStock);

            if (ingreServ.addIngredient(ingre)) {
                viewPage = "ingredient.jsp";
                msg = "Successfully add the ingredient";
            } else {
                viewPage = "ingredient.jsp";
                msg = "Failed to Add the ingredient";
            }

        } catch (ArgumentException ex) {
            viewPage = "ingredient.jsp";
            msg = ex.getMessage();
        } catch (NumberFormatException ev) {
            viewPage = "ingredient.jsp";
            msg = "Cannot accept number for minimum stock on Hand value";
        }

        request.setAttribute("ingredients", ingreServ.getAllIngredients());

    }

    private void editIngredient(HttpServletRequest request, HttpServletResponse response) {
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl.getInstance(dbm.getConnection()));

        String ingrId = request.getParameter("id");
        String name = request.getParameter("ingred-name");
        String minStock = request.getParameter("min-stock");
        String stock = request.getParameter("stock");
        String active = request.getParameter("is-active");
        String uni = request.getParameter("unit");

        System.out.println("Active: " + active);
        System.out.println("Unit: " + uni);
        viewPage = "ingredient.jsp";
        try {

            Unit unit = Unit.valueOf(uni);

            int ingredientId = Integer.parseInt(ingrId);

            double minStockVal = 0.0;
            if (minStock != null && !minStock.isEmpty()) {
                try {
                    minStockVal = Double.parseDouble(minStock);
                } catch (NumberFormatException e) {
                    request.setAttribute("ingredients", ingreServ.getAllIngredients());
                    msg = "invalid number";
                    return;
                }
            }

            Ingredient ingre = new Ingredient(ingredientId, name, Double.parseDouble(stock), minStockVal, Boolean.parseBoolean("is-active"), unit);
            ingre.setIngredientId(ingredientId);
            ingre.setName(name);
            ingre.setMinStockOnHand(minStockVal);
            ingre.setIsActive(Boolean.parseBoolean(active));
            ingre.setStock(Double.parseDouble(stock));
            ingre.setUnit(Unit.valueOf(uni));

            if (ingreServ != null && ingreServ.updateIngredient(ingre)) {
                msg = "Ingredient Successfully Updated";
            } else {
                msg = "Could not update Ingredient";
            }

        } catch (ArgumentException ex) {
            viewPage = "ingredient.jsp";
            msg = ex.getMessage();
        } catch (NumberFormatException nx) {
            viewPage = "ingredient.jsp";
            msg = "Invalid number format for minimum stock on hand";
        }
        request.setAttribute("ingredients", ingreServ.getAllIngredients());

    }

    private void activateIngredient(HttpServletRequest request, HttpServletResponse response) {
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl.getInstance(dbm.getConnection()));
        request.setAttribute("ingredients", ingreServ.getAllIngredients());

        String ingredientyId = request.getParameter("ingredientId");
        String active = request.getParameter("active");
        viewPage = "ingredient.jsp";
        int id;
        boolean isActive;

        try {
            id = Integer.parseInt(ingredientyId);
            isActive = Boolean.parseBoolean(active);

        } catch (NumberFormatException numberFormatException) {
            msg = "Invalid ingredient Id ";
            request.setAttribute("ingredients", ingreServ.getAllIngredients());
            return;
        }

        try {
            if (ingreServ.deleteIngredient(id, isActive)) {
                msg = !isActive ? "Ingredient de-activated" : "Ingredient activated";
            } else {
                msg = isActive ? "failed to de-activate ingredient" : "failed to activate ingredient";
            }
        } catch (ArgumentException ex) {
            Logger.getLogger(AdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("ingredients", ingreServ.getAllIngredients());

    }

    private void returnAlltheIngredients(HttpServletRequest request, HttpServletResponse response) {
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl
                .getInstance(dbm.getConnection()));

        viewPage = "ingredient.jsp";
        request.setAttribute("ingredients", ingreServ.getAllIngredients());

    }

    private void returnAllTheCategories(HttpServletRequest request, HttpServletResponse response) {
        catServ = CategoryServiceImpl.getInstance(CategoryDaoImpl
                .getInstance(dbm.getConnection()));

        viewPage = "category.jsp";
        request.setAttribute("categories", catServ.getAllCategories());
    }

    private void createIngredientsOrder(HttpServletRequest request, HttpServletResponse response) {
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl
                .getInstance(dbm.getConnection()));
        ingreOrderServ = Ingredient_OrderServiceImpl.getInstance(Ingredient_OrderDaoImpl
                .getInstance(dbm.getConnection()), IngredientDaoImpl
                .getInstance(dbm.getConnection()));
        viewPage = "ingredient-order.jsp";

        String[] ingredientsIds = request.getParameterValues("ingredient[]");
        String[] quantities = request.getParameterValues("quantity[]");

        Ingredient_Order order = new Ingredient_Order();
        List<Ingredient_OrderItem> items = new ArrayList<>();
        boolean isValid = true; //(ingredientsIds!=null && ingredientsIds.length!=0) &&(quantities!=null && quantities.length!=0);
        Ingredient ingred = null;
        int cnt = 0, id = -1;
        double qty = -1;
        System.out.println("Ready to Order Ingredient: " + isValid);

        System.out.println(Arrays.toString(ingredientsIds));
        System.out.println(Arrays.toString(quantities));

        while (isValid && cnt < ingredientsIds.length) {

            try {
                qty = Double.parseDouble(quantities[cnt]);
                id = Integer.parseInt(ingredientsIds[cnt]);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException nm) {

                isValid = false;
                continue;
            }

            try {
                isValid = (ingred = ingreServ.getIngredientById(id)) != null;

                if (isValid) {
                    items.add(new Ingredient_OrderItem(qty, ingred));
                }
            } catch (ArgumentException ex) {
                isValid = false;
                continue;
            }

            cnt++;
        }

        if (isValid) {
            order = new Ingredient_Order(LocalDateTime.now(), false, items);
            items.stream().forEach(System.out::println);

            if (ingreOrderServ.addIngredient_Order(order)) {
                msg = "Successfully Ordered";

            } else {
                msg = "Failed to Order";
            }

        } else {
            msg = "Failed to Order";
        }
        request.setAttribute("ingredients", ingreServ.getAllIngredients());
        request.setAttribute("ingredient-orders", ingreOrderServ.getAllIngredient_Orders());

    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {

        catServ = CategoryServiceImpl.getInstance(CategoryDaoImpl.getInstance(dbm.getConnection()));
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl.getInstance(dbm.getConnection()));
        proServ = ProductServiceImpl.getInstance(ProductDaoImpl.getInstance(dbm.getConnection()));

        String catIdStr = request.getParameter("cat-id");
        String prodName = request.getParameter("pro-name");
        String recipeName = request.getParameter("res-name");
        String recipeDec = request.getParameter("res-desc");
        String warnings = request.getParameter("pro-warnings");
        String nutritionInfo = request.getParameter("pro-nutro-info");
        String unitPriceStr = request.getParameter("pro-price");
        String image = request.getParameter("image");

        String[] ingredientsIds = request.getParameterValues("ingredient[]");
        String[] units = request.getParameterValues("unit[]");
        String[] quantities = request.getParameterValues("quantity[]");
        Product prod = null;
        Category cat = null;
        Recipe res = null;
        Unit unit = null;
        boolean flag;
        int cnt = 0, id = -1, unitId;
        double qty = -1, unitPrice = -1;

        if (ingredientsIds == null || quantities == null) {
            viewPage = "adding-product.jsp";
            request.setAttribute("msg", "Please specify ingredients when Adding product");
            return;
        }

        try {
            int catId = Integer.parseInt(catIdStr);
            unitPrice = Double.parseDouble(unitPriceStr);

            flag = (cat = catServ.getCategoryById(catId)) != null;

        } catch (NumberFormatException | ArgumentException nfm) {
            flag = false;
            nfm.printStackTrace();
        }

        Ingredient ingred = null;
        HashMap<Ingredient, QuantityUnit> ingredients = new HashMap<>();

        while (flag && cnt < ingredientsIds.length) {

            try {
                qty = Double.parseDouble(quantities[cnt]);
                unitId = Integer.parseInt(units[cnt]);
                id = Integer.parseInt(ingredientsIds[cnt]);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException nm) {
                nm.printStackTrace();
                flag = false;
                continue;
            }
            try {
                flag = (ingred = ingreServ.getIngredientById(id)) != null && (unit = Unit.values()[unitId]) != null;

                if (flag) {
                    ingredients.put(ingred, new QuantityUnit(qty, unit));
                }
            } catch (ArgumentException ex) {
                ex.printStackTrace();
                flag = false;
                continue;

            }

            cnt++;
        }

        if (flag) {
            prod = new Product();
            prod.setUnitPrice(unitPrice);
            prod.setNutrientInfo(nutritionInfo);
            prod.setName(prodName);
            prod.setWarnings(warnings);
            prod.setCategory(cat);
            prod.setIsActive(true);
            prod.setImage(image);
            prod.setRecipe(new Recipe(recipeName, recipeDec, ingredients));

            if (proServ.addProductNew(prod)) {
                msg = "Product Added successfully";
                //viewPage = "page.jsp";
            } else {
                msg = "Failed to Add Product";
                //viewPage = "add-product.jsp";
            }

        } else {
            msg = "Server Error: Failed to Add Product";
            //viewPage = "add-product.jsp";
        }
        viewPage = "product.jsp";
        catServ = CategoryServiceImpl.getInstance(CategoryDaoImpl.getInstance(dbm.getConnection()));
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl.getInstance(dbm.getConnection()));

        request.setAttribute("categories", catServ.getAllCategories());
        request.setAttribute("ingredients", ingreServ.getAllIngredients());

        request.setAttribute("products", proServ.getAllProducts());
        request.setAttribute("msg", msg);

    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) {
        proServ = ProductServiceImpl.getInstance(ProductDaoImpl.getInstance(dbm.getConnection()));

        String prodId = request.getParameter("id");
        String name = request.getParameter("product-name");
        String price = request.getParameter("pro-price");
        String nutrient = request.getParameter("nutrient-info");
        String warnings = request.getParameter("warnings");
        String active = request.getParameter("active");
        // String uni = request.getParameter("unit");

        viewPage = "product.jsp";
        try {

            int productId = Integer.parseInt(prodId);
            double proPrice = Double.parseDouble(price);
            boolean isActive = Boolean.parseBoolean(active);

            Product prod = new Product(productId, name, nutrient, warnings, proPrice, isActive);
            prod.setName(name);
            prod.setUnitPrice(proPrice);
            prod.setNutrientInfo(nutrient);
            prod.setWarnings(warnings);
            prod.setIsActive(isActive);

            if (proServ != null && proServ.updateProduct(prod)) {
                viewPage = "product.jsp";
                msg = "Product Successfully Updated";
            } else {
                viewPage = "product.jsp";
                msg = "Could not update roduct";
            }

        } catch (ArgumentException ex) {
            viewPage = "product.jsp";
            msg = ex.getMessage();
        } catch (NumberFormatException nx) {
            viewPage = "product.jsp";
            msg = "Invalid ProductId";
        }
        viewPage = "product.jsp";
        request.setAttribute("products", proServ.getAllProducts());
    }

    private void activateProduct(HttpServletRequest request, HttpServletResponse response) {
        proServ = ProductServiceImpl.getInstance(ProductDaoImpl.getInstance(dbm.getConnection()));

        request.setAttribute("products", proServ.getAllProducts());

        String productId = request.getParameter("productId");
        String active = request.getParameter("active");
        viewPage = "product.jsp";

        int id;
        boolean isActive;

        try {
            id = Integer.parseInt(productId);
            isActive = Boolean.parseBoolean(active);

        } catch (NumberFormatException numberFormatException) {
            msg = "Invalid Product Id ";
            request.setAttribute("products", proServ.getAllProducts());
            return;
        }

        if (proServ.deactivateProduct(id, isActive)) {
            msg = !isActive ? "Product de-activated" : "Product activated";
        } else {
            msg = isActive ? "failed to de-activate Product" : "failed to activate Product";
        }
        request.setAttribute("products", proServ.getAllProducts());
    }

    private void createProductOrder(HttpServletRequest request, HttpServletResponse response) {
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl.getInstance(dbm.getConnection()));
        viewPage = "order-ingredient.jsp";

        request.setAttribute("ingredients", ingreServ.getAllIngredients());
    }

    private void addAllRequiredResourceForAddingProduct(HttpServletRequest request, HttpServletResponse response) {
        catServ = CategoryServiceImpl.getInstance(CategoryDaoImpl.getInstance(dbm.getConnection()));
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl.getInstance(dbm.getConnection()));
        viewPage = "add-product.jsp";

        request.setAttribute("categories", catServ.getAllCategories());
        request.setAttribute("ingredients", ingreServ.getAllIngredients());
    }

    private void addAllRequiredResourceForOrderingIngredients(HttpServletRequest request, HttpServletResponse response) {
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl.getInstance(dbm.getConnection()));

        viewPage = "order-ingredient.jsp";
        request.setAttribute("ingredients", ingreServ.getAllIngredients());

    }

    private void returnAlltheIngredientsOrders(HttpServletRequest request, HttpServletResponse response) {
        ingreOrderServ = Ingredient_OrderServiceImpl.getInstance(Ingredient_OrderDaoImpl
                .getInstance(dbm.getConnection()), IngredientDaoImpl
                .getInstance(dbm.getConnection()));
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl
                .getInstance(dbm.getConnection()));

        viewPage = "ingredient-order.jsp";
        request.setAttribute("ingredients", ingreServ.getAllIngredients());
        request.setAttribute("ingredient-orders", ingreOrderServ.getAllIngredient_Orders());
    }

    private void getAllProducts(HttpServletRequest request, HttpServletResponse response) {
        catServ = CategoryServiceImpl.getInstance(CategoryDaoImpl.getInstance(dbm.getConnection()));
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl.getInstance(dbm.getConnection()));

        request.setAttribute("categories", catServ.getAllCategories());
        request.setAttribute("ingredients", ingreServ.getAllIngredients());

        proServ = ProductServiceImpl.getInstance(ProductDaoImpl.getInstance(dbm.getConnection()));

        viewPage = "product.jsp";
        request.setAttribute("products", proServ.getAllProducts());
    }

    private void returnAlltheOrders(HttpServletRequest request, HttpServletResponse response) {
        orderServ = OrderServiceImpl.getInstance(OrderDaoImpl.getInstance(dbm.getConnection()), IngredientDaoImpl.getInstance(dbm.getConnection()),null);
        viewPage = "order.jsp";
        request.setAttribute("orders", orderServ.getAllOrders());
    }

    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) {
        orderServ = OrderServiceImpl.getInstance(OrderDaoImpl.getInstance(dbm.getConnection()), IngredientDaoImpl.getInstance(dbm.getConnection()),null);

        String ordId = request.getParameter("orderId");
        String status = request.getParameter("status");
        viewPage = "order.jsp";

        try {
            int id = Integer.parseInt(ordId);
            Status orderStatus = Status.valueOf(status);

            Order order = new Order(id, orderStatus);

            order.setOrderId(id);
            order.setStatus(orderStatus);
            
            if(orderServ != null && orderServ.updateStatus(id, orderStatus)){
                msg="Order Status updated";
            }else{
                msg="Faild to update order";
            }

        } catch (NumberFormatException e) {
            
            msg = "Invalid Id";
        }
        request.setAttribute("orders", orderServ.getAllOrders());
    }

    private void confirmOrder(HttpServletRequest request, HttpServletResponse response) {
        ingreOrderServ = Ingredient_OrderServiceImpl.getInstance(Ingredient_OrderDaoImpl
                .getInstance(dbm.getConnection()), IngredientDaoImpl
                .getInstance(dbm.getConnection()));
        ingreServ = IngredientServiceImpl.getInstance(IngredientDaoImpl
                .getInstance(dbm.getConnection()));
        request.setAttribute("ingredients", ingreServ.getAllIngredients());

        String msg = request.getParameter("ingredOrderId");
        viewPage = "ingredient-order.jsp";
        int id;

        try {
            id = Integer.parseInt(msg);

        } catch (NumberFormatException numberFormatException) {
            msg = "Invalid Order Id ";

            request.setAttribute("ingredient-orders", ingreOrderServ.getAllIngredient_Orders());
            return;

        }

        if (ingreOrderServ.confirmIngredientOrderDelivery(id)) {
            msg = "Ingredients updated!!!";
        } else {
            msg = "failed to update order";
        }

        request.setAttribute("ingredient-orders", ingreOrderServ.getAllIngredient_Orders());

    }

    private void downloadDeliveryNote(HttpServletRequest request, HttpServletResponse response) {
        returnAlltheOrders(request, response);
        int orderId;
        
        
        try {
            
            orderId = Integer.parseInt(request.getParameter("order-id"));
            
            
            
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"delivery-note-"+orderId+".pdf\"");
            orderServ.prepareDeliveryNote(response, orderId);
            
            
        } catch (NumberFormatException | IOException ex) {
            msg= "Error Occured";
        }
        
        
    }

}
//service?service=admin&process=all-orders
