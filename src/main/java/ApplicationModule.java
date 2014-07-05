import com.foodit.test.sample.calculator.OrderReporter;
import com.foodit.test.sample.entities.RestaurantData;
import com.foodit.test.sample.entities.RestaurantOrderReport;
import com.foodit.test.sample.service.KeyedRestaurantMenuData;
import com.foodit.test.sample.service.KeyedRestaurantMenuDataImpl;
import com.foodit.test.sample.service.RestaurantDataService;
import com.foodit.test.sample.service.RestaurantDataServiceImpl;
import com.googlecode.objectify.ObjectifyService;
import com.threewks.thundr.gae.GaeModule;
import com.threewks.thundr.gae.objectify.ObjectifyModule;
import com.threewks.thundr.injection.BaseModule;
import com.threewks.thundr.injection.UpdatableInjectionContext;
import com.threewks.thundr.module.DependencyRegistry;
import com.threewks.thundr.route.Routes;

public class ApplicationModule extends BaseModule {
	private ApplicationRoutes applicationRoutes = new ApplicationRoutes();

	@Override
	public void requires(DependencyRegistry dependencyRegistry) {
		super.requires(dependencyRegistry);
		dependencyRegistry.addDependency(GaeModule.class);
		dependencyRegistry.addDependency(ObjectifyModule.class);
	}

	@Override
	public void configure(UpdatableInjectionContext injectionContext) {
		super.configure(injectionContext);
		configureObjectify();
        injectionContext.inject(RestaurantDataServiceImpl.class).as(RestaurantDataService.class);
        injectionContext.inject(OrderReporter.class).as(OrderReporter.class);
        injectionContext.inject(KeyedRestaurantMenuDataImpl.class).as(KeyedRestaurantMenuData.class);

    }

	@Override
	public void start(UpdatableInjectionContext injectionContext) {
		super.start(injectionContext);
		Routes routes = injectionContext.get(Routes.class);
		applicationRoutes.addRoutes(routes);
	}

	private void configureObjectify() {

        ObjectifyService.register(RestaurantData.class);
        ObjectifyService.register(RestaurantOrderReport.class);
	}
}
