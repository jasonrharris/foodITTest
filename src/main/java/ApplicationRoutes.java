import com.foodit.test.sample.controller.DataLoadController;
import com.foodit.test.sample.controller.OrderReportController;
import com.threewks.thundr.action.method.MethodAction;
import com.threewks.thundr.route.Route;
import com.threewks.thundr.route.Routes;

import static com.threewks.thundr.route.RouteType.GET;

public class ApplicationRoutes {
	public enum RouteName {
		LIST("list"),
        CREATE("create-task"),
        VIEW_TASK("view-task"),
        UPDATE("update-task"),
        START("start-task"),
        STOP("stop-task"),
        FINISHED("finished-task"),
        ARCHIVE("archive-task"),
        LOAD_DATA("load-data"),
        VIEW_INSTRUCTIONS("view-instructions"),
        VIEW_DATA("view-data"),
        ORDER_COUNT("order-count"),
        SALES("sales"),
        MOST_POPULAR_CATEGORY("most-pop-category"),
        MOST_FREQUENT_ITEM("most-frequent-item");

        private String name;

        RouteName(String name) {

            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

	public void addRoutes(Routes routes) {

		// Loader
		routes.addRoute(new Route(GET, "/load/", RouteName.LOAD_DATA.getName()), new MethodAction(DataLoadController.class, "load"));

		// Instructions
		routes.addRoute(new Route(GET, "/", RouteName.VIEW_INSTRUCTIONS.getName()), new MethodAction(DataLoadController.class, "instructions"));

		routes.addRoute(new Route(GET, "/restaurant/{restaurant}/download", RouteName.VIEW_DATA.getName()), new MethodAction(DataLoadController.class, "viewData"));

        //Data Enquiries
        routes.addRoute(new Route(GET, "/restaurant/{restaurant}/orders/count", RouteName.ORDER_COUNT.getName()), new MethodAction(OrderReportController.class, "orderCountReport"));

        routes.addRoute(new Route(GET, "/restaurant/{restaurant}/sales", RouteName.SALES.getName()), new MethodAction(OrderReportController.class, "orderSalesReport"));

        routes.addRoute(new Route(GET, "/restaurant/{restaurant}/mostPopularCategory", RouteName.MOST_POPULAR_CATEGORY.getName()), new MethodAction(OrderReportController.class, "mostPopularCategoryReport"));

        routes.addRoute(new Route(GET, "/platform/mostFrequentItems", RouteName.MOST_FREQUENT_ITEM.getName()), new MethodAction(OrderReportController.class, "mostPopularItemByRestaurant"));

    }
}
