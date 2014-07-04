package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.Order;
import com.foodit.test.sample.entities.RestaurantData;
import com.threewks.thundr.gae.SetupAppengine;
import com.threewks.thundr.gae.objectify.SetupObjectify;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestaurantDataServiceImplTest {

    public static final String BBQGRILL = "bbqgrill";
    public static final String BQGRILL_MENU = "{\"menu\":{\"Kebabs (Kebabs)\":[{\"id\":0,\"name\":\"Lamb Kebab\",\"description\":\"Cubes of lamb cooked on charcoal served in pitta bread and salad\",\"category\":\"Kebabs\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Size\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"Small\",\"pricesForSize\":{\"Regular\":\"4.50\"}},{\"label\":\"Large\",\"pricesForSize\":{\"Regular\":\"6.50\"}}]},{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]},{\"name\":\"Drinks\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Coke\",\"pricesForSize\":{\"Regular\":\"0.80\"}},{\"label\":\"Diet Coke\",\"pricesForSize\":{\"Regular\":\"0.80\"}},{\"label\":\"Pepsi\",\"pricesForSize\":{\"Regular\":\"0.80\"}},{\"label\":\"7UP\",\"pricesForSize\":{\"Regular\":\"0.80\"}}]}],\"startingFromPrice\":\"4.50\"},{\"id\":1,\"name\":\"Pork Kebab\",\"description\":\"Cubes of pork cooked on charcoal served in pitta bread and salad\",\"category\":\"Kebabs\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Size\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"Small\",\"pricesForSize\":{\"Regular\":\"4.00\"}},{\"label\":\"Large\",\"pricesForSize\":{\"Regular\":\"5.50\"}}]},{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]},{\"name\":\"Drinks\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Coke\",\"pricesForSize\":{\"Regular\":\"0.80\"}},{\"label\":\"Diet Coke\",\"pricesForSize\":{\"Regular\":\"0.80\"}},{\"label\":\"Pepsi\",\"pricesForSize\":{\"Regular\":\"0.80\"}},{\"label\":\"7UP\",\"pricesForSize\":{\"Regular\":\"0.80\"}}]}],\"startingFromPrice\":\"4.00\"},{\"id\":2,\"name\":\"Chicken Kebab\",\"description\":\"Cubes of chicken cooked on charcoal served in pitta bread and salad\",\"category\":\"Kebabs\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Size\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"Small\",\"pricesForSize\":{\"Regular\":\"4.00\"}},{\"label\":\"Large\",\"pricesForSize\":{\"Regular\":\"5.50\"}}]},{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"4.00\"},{\"id\":3,\"name\":\"Sheftalia\",\"description\":\"Traditional Cypriot dish which is a sausage without skin charcoal grilled served in pitta bread and salad\",\"category\":\"Kebabs\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Size\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"Small\",\"pricesForSize\":{\"Regular\":\"4.00\"}},{\"label\":\"Large\",\"pricesForSize\":{\"Regular\":\"5.50\"}}]},{\"name\":\"SIde\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"4.00\"},{\"id\":4,\"name\":\"Shish Kofte\",\"description\":\"Lamb mincemeat with herbs\",\"category\":\"Kebabs\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Size\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"Small\",\"pricesForSize\":{\"Regular\":\"4.00\"}},{\"label\":\"Large\",\"pricesForSize\":{\"Regular\":\"5.50\"}}]},{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"4.00\"},{\"id\":5,\"name\":\"Mixed Kebab\",\"description\":\"\",\"category\":\"Kebabs\",\"sizeAndPrice\":{\"Regular\":\"7.50\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"7.50\"},{\"id\":6,\"name\":\"BBQ Wings\",\"description\":\"\",\"category\":\"Kebabs\",\"sizeAndPrice\":{\"Regular\":\"6.00\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"6.00\"}],\"Burgers (Burgers)\":[{\"id\":7,\"name\":\"Beefy Burger Quarter Pounder\",\"description\":\"\",\"category\":\"Burgers\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Choice\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}},{\"label\":\"No Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]},{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}},{\"label\":\"Cheese On Burger\",\"pricesForSize\":{\"Regular\":\"0.30\"}}]}],\"startingFromPrice\":\"3.00\"},{\"id\":8,\"name\":\"Chicken Burger\",\"description\":\"Fresh chicken breast\",\"category\":\"Burgers\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Choice\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}},{\"label\":\"No Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]},{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}},{\"label\":\"Cheese On Burger\",\"pricesForSize\":{\"Regular\":\"0.30\"}}]}],\"startingFromPrice\":\"3.00\"}],\"Ribs (Ribs)\":[{\"id\":38,\"name\":\"Lamb Ribs\",\"description\":\"Tender Cooked Lamb Ribs\",\"category\":\"Ribs\",\"sizeAndPrice\":{\"Regular\":\"10.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"10.00\"}],\"Pitta Snacks (PittaSnacks)\":[{\"id\":9,\"name\":\"Halloumi Pitta \\u0026amp; Salad\",\"description\":\"Cyprus Cheese, grilled\",\"category\":\"Pitta Snacks\",\"sizeAndPrice\":{\"Regular\":\"4.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"4.00\"},{\"id\":10,\"name\":\"Lounza Pitta \\u0026amp; Salad\",\"description\":\"smoked pork loin slices\",\"category\":\"Pitta Snacks\",\"sizeAndPrice\":{\"Regular\":\"4.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"4.00\"},{\"id\":11,\"name\":\"Loukanico Pitta \\u0026amp; salad\",\"description\":\"Cyprus Sausage\",\"category\":\"Pitta Snacks\",\"sizeAndPrice\":{\"Regular\":\"4.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"4.00\"},{\"id\":12,\"name\":\"Pastourma Pitta \\u0026amp; salad\",\"description\":\"Spicy sausage\",\"category\":\"Pitta Snacks\",\"sizeAndPrice\":{\"Regular\":\"4.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"4.00\"},{\"id\":13,\"name\":\"Salad and Pitta\",\"description\":\"\",\"category\":\"Pitta Snacks\",\"sizeAndPrice\":{\"Regular\":\"2.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"2.00\"}],\"Meze (Meze)\":[{\"id\":14,\"name\":\"Meze for Two\",\"description\":\"Meze is a selection of small dishes\",\"category\":\"Meze\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"People\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"2 people\",\"pricesForSize\":{\"Regular\":\"24.00\"}},{\"label\":\"3 people\",\"pricesForSize\":{\"Regular\":\"36.00\"}},{\"label\":\"4 people\",\"pricesForSize\":{\"Regular\":\"48.00\"}},{\"label\":\"5 people\",\"pricesForSize\":{\"Regular\":\"60.00\"}}]}],\"startingFromPrice\":\"24.00\"}],\"Souvla (Souvla)\":[{\"id\":15,\"name\":\"Lamb Souvla\",\"description\":\"Pieces of lamb cooked on charcoal. 30 minutes to cook\",\"category\":\"Souvla\",\"sizeAndPrice\":{\"Regular\":\"9.50\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]},{\"name\":\"Drinks\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Coke\",\"pricesForSize\":{\"Regular\":\"0.80\"}},{\"label\":\"Diet Coke\",\"pricesForSize\":{\"Regular\":\"0.80\"}},{\"label\":\"Pepsi\",\"pricesForSize\":{\"Regular\":\"0.80\"}},{\"label\":\"7UP\",\"pricesForSize\":{\"Regular\":\"0.80\"}}]}],\"startingFromPrice\":\"9.50\"},{\"id\":16,\"name\":\"Chicken Souvla\",\"description\":\"Pieces of Chicken cooked on charcoal. 30 minutes to cook\",\"category\":\"Souvla\",\"sizeAndPrice\":{\"Regular\":\"7.00\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"7.00\"},{\"id\":17,\"name\":\"Pork Souvla\",\"description\":\"Pieces of Pork cooked on charcoal. 30 minutes to cook\",\"category\":\"Souvla\",\"sizeAndPrice\":{\"Regular\":\"7.50\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"7.50\"},{\"id\":18,\"name\":\"Lamb Chops\",\"description\":\"\",\"category\":\"Souvla\",\"sizeAndPrice\":{\"Regular\":\"10.00\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"10.00\"}],\"Specials of the Day (SpecialsoftheDay)\":[{\"id\":19,\"name\":\"Kleftiko\",\"description\":\"Lamb slowly cooked in the oven\",\"category\":\"Specials of the Day\",\"sizeAndPrice\":{\"Regular\":\"9.00\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"9.00\"},{\"id\":20,\"name\":\"Keftedes\",\"description\":\"Pork meat balls with bers and spices, fried\",\"category\":\"Specials of the Day\",\"sizeAndPrice\":{\"Regular\":\"5.50\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],\"startingFromPrice\":\"5.50\"},{\"id\":21,\"name\":\"Dolmades\",\"description\":\"Stuffed vine leaves with mincemeat, rice \\u0026 spices\",\"category\":\"Specials of the Day\",\"sizeAndPrice\":{\"Regular\":\"5.50\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"5.50\"},{\"id\":22,\"name\":\"Mousaka\",\"description\":\"Layers of vegtables and mincemeat and cream top\",\"category\":\"Specials of the Day\",\"sizeAndPrice\":{\"Regular\":\"6.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"6.00\"},{\"id\":23,\"name\":\"Vegetarian Mousaka\",\"description\":\"Layers of vegetables and cream top\",\"category\":\"Specials of the Day\",\"sizeAndPrice\":{\"Regular\":\"5.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"5.00\"}],\"Sea food (Seafood)\":[{\"id\":24,\"name\":\"Kalamari Fried\",\"description\":\"Rings of kalamari deep fried\",\"category\":\"Sea food\",\"sizeAndPrice\":{\"Regular\":\"6.50\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"6.50\"},{\"id\":25,\"name\":\"Seabass\",\"description\":\"\",\"category\":\"Sea food\",\"sizeAndPrice\":{\"Regular\":\"9.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"9.00\"},{\"id\":26,\"name\":\"Scampi\",\"description\":\"12 pieces\",\"category\":\"Sea food\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"No Side\",\"pricesForSize\":{\"Regular\":\"6.00\"}},{\"label\":\"With Rice\",\"pricesForSize\":{\"Regular\":\"7.50\"}},{\"label\":\"With Chips\",\"pricesForSize\":{\"Regular\":\"7.50\"}},{\"label\":\"With Greek Salad\",\"pricesForSize\":{\"Regular\":\"9.00\"}}]}],\"startingFromPrice\":\"6.00\"},{\"id\":27,\"name\":\"Sea Bream\",\"description\":\"\",\"category\":\"Sea food\",\"sizeAndPrice\":{\"Regular\":\"9.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"9.00\"}],\"Side Orders (SideOrders)\":[{\"id\":28,\"name\":\"Taramonsalata\",\"description\":\"Smoked cod roe, olive oil \\u0026 lemon juice\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"1.50\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"1.50\"},{\"id\":29,\"name\":\"Houmous\",\"description\":\"Chick peas, tahini, oil \\u0026 a hint of garlic\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"1.50\"},\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"No Side\",\"pricesForSize\":{\"Regular\":\"0.00\"}}]}],\"startingFromPrice\":\"1.50\"},{\"id\":30,\"name\":\"Txatziki/Cacik\",\"description\":\"Yoghurt with cucumber, mind, oliver oil \\u0026 garlic\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"1.50\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"1.50\"},{\"id\":31,\"name\":\"Tahini\",\"description\":\"Sesame seed dip with lemon juice \\u0026 garlic\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"1.50\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"1.50\"},{\"id\":32,\"name\":\"Potato Salad\",\"description\":\"New potatoes, olive oil \\u0026 parsley\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"1.50\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"1.50\"},{\"id\":33,\"name\":\"Roast Potatoes\",\"description\":\"\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"1.50\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"1.50\"},{\"id\":34,\"name\":\"Rice\",\"description\":\"\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"1.50\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"1.50\"},{\"id\":35,\"name\":\"Greek Salad\",\"description\":\"\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"3.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"3.00\"},{\"id\":36,\"name\":\"Chips\",\"description\":\"Americans call it French Fries, Europeans call it Pommes.. but we call it Chips\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"1.50\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"1.50\"},{\"id\":37,\"name\":\"Baclava\",\"description\":\"Baclava rich, sweet pastry made of layers of filo pastry filled with chopped nuts and sweetened.\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"2.00\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"2.00\"},{\"id\":46,\"name\":\"Pick 1 Side\",\"description\":\"Pick from one the following sides\",\"category\":\"Side Orders\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Pick a Side\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"Taramonsalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Txatziki / Cacik\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tahini\",\"pricesForSize\":{\"Regular\":\"1.50\"}}]}],\"startingFromPrice\":\"1.50\"}],\"Drinks (Drinks)\":[{\"id\":40,\"name\":\"Coke Can\",\"description\":\"Coca Cola Can\",\"category\":\"Drinks\",\"sizeAndPrice\":{\"Regular\":\"0.80\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"0.80\"},{\"id\":41,\"name\":\"Cola 1.5 Litre Large Bottle\",\"description\":\"Coca Cola 1.5lt Bottle\",\"category\":\"Drinks\",\"sizeAndPrice\":{\"Regular\":\"0.00\"},\"mealTypeOptions\":[{\"name\":\"Pick Drink\",\"multiSelect\":false,\"dropDown\":false,\"options\":[{\"label\":\"Coca Cola 1.5 Litre\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Diet Cola 1.5 Litre\",\"pricesForSize\":{\"Regular\":\"1.50\"}}]}],\"startingFromPrice\":\"1.50\"},{\"id\":42,\"name\":\"Diet Coca Cola Can\",\"description\":\"Diet Coca Cola 330ml Can\",\"category\":\"Drinks\",\"sizeAndPrice\":{\"Regular\":\"0.80\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"0.80\"},{\"id\":44,\"name\":\"Pepsi\",\"description\":\"Pepsi 330ml tin can\",\"category\":\"Drinks\",\"sizeAndPrice\":{\"Regular\":\"0.80\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"0.80\"},{\"id\":45,\"name\":\"7up can\",\"description\":\"7up 330ml tin can\",\"category\":\"Drinks\",\"sizeAndPrice\":{\"Regular\":\"0.80\"},\"mealTypeOptions\":[],\"startingFromPrice\":\"0.80\"}]},\"restaurantId\":\"bbqgrill\"}";

    private final RestaurantData restaurantData = new RestaurantData(
            BBQGRILL,
            BQGRILL_MENU,
            getOrdersJson());

    public static final String BQGRILL_ORDER_1 = "{\"recVersion\":7,\"orderId\":5456722831343616,\"easyOrderNum\":1,\"created\":\"2014-03-23T19:06:37.459Z\",\"storeId\":\"bbqgrill\",\"storeName\":\"B-B-Q Grill\",\"totalValue\":9.50,\"lineItems\":[{\"id\":5,\"total\":\"7.50\",\"unitPrice\":\"7.50\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[]},{\"id\":37,\"total\":\"2.00\",\"unitPrice\":\"2.00\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[]}],\"status\":\"PaymentCaptured\",\"timeZoneId\":\"Europe/London\",\"collectionType\":\"Collection\",\"paymentType\":\"CREDIT_CARD\",\"addressDisplay\":\"1260 High Rd, London, N20 9HH\",\"contactNumber\":\"+442084467888\",\"domain\":\"www.bbqgrillwhetstone.co.uk\",\"currencyDisplay\":\"GBP\",\"commissionRate\":5,\"referenceNumber\":\"1\",\"receiptLogoUrl\":\"http://foodit-prod.appspot.com/api/v1/serveLogo/AMIfv94d6PQjKmDQU27RTz8vDRqEzeTwgsXXDKpNxfxcVJSGSI_8JFq_XHTbHV_ggLv2GFLFGXaAhekq0B2J0S9Vq3emJuJnB6KgQE2rUvaawdxjng6V5sDPg6brOktvZAsW22q23vuolFCOwsu54kIsAHDI7OpUmw/\"}";
    public static final String BBQ_GRILL_ORDER_2 = "{\"recVersion\":6,\"orderId\":5277395061833728,\"easyOrderNum\":1,\"created\":\"2014-03-24T21:15:06.696Z\",\"storeId\":\"bbqgrill\",\"storeName\":\"B-B-Q Grill\",\"totalValue\":7.30,\"lineItems\":[{\"id\":0,\"total\":\"7.30\",\"unitPrice\":\"7.30\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[{\"name\":\"Size\",\"value\":\"Large\"},{\"name\":\"Drinks\",\"value\":\"Pepsi\"}]}],\"status\":\"CanceledByRestaurant\",\"timeZoneId\":\"Europe/London\",\"collectionType\":\"Collection\",\"paymentType\":\"CASH\",\"addressDisplay\":\"1260 High Rd, London, N20 9HH\",\"contactNumber\":\"+442084467888\",\"domain\":\"www.bbqgrillwhetstone.co.uk\",\"currencyDisplay\":\"GBP\",\"commissionRate\":5,\"referenceNumber\":\"1\",\"receiptLogoUrl\":\"http://foodit-prod.appspot.com/api/v1/serveLogo/AMIfv94d6PQjKmDQU27RTz8vDRqEzeTwgsXXDKpNxfxcVJSGSI_8JFq_XHTbHV_ggLv2GFLFGXaAhekq0B2J0S9Vq3emJuJnB6KgQE2rUvaawdxjng6V5sDPg6brOktvZAsW22q23vuolFCOwsu54kIsAHDI7OpUmw/\"}";
    public static final String BBQ_GRILL_ORDER_3 = "{\"recVersion\":6,\"orderId\":5278470951141376,\"easyOrderNum\":1,\"created\":\"2014-03-26T18:00:45.566Z\",\"storeId\":\"bbqgrill\",\"storeName\":\"B-B-Q Grill\",\"totalValue\":20.00,\"lineItems\":[{\"id\":1,\"total\":\"5.50\",\"unitPrice\":\"5.50\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[{\"name\":\"Size\",\"value\":\"Large\"}]},{\"id\":6,\"total\":\"6.00\",\"unitPrice\":\"6.00\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[]},{\"id\":20,\"total\":\"5.50\",\"unitPrice\":\"5.50\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[]},{\"id\":30,\"total\":\"1.50\",\"unitPrice\":\"1.50\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[]},{\"id\":36,\"total\":\"1.50\",\"unitPrice\":\"1.50\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[]},{\"id\":46,\"total\":\"0.00\",\"unitPrice\":\"0.00\",\"quantity\":1,\"promotion\":true,\"mealOptions\":[{\"name\":\"Pick a Side\",\"value\":\"Taramonsalata\"}]}],\"status\":\"Collected\",\"timeZoneId\":\"Europe/London\",\"collectionType\":\"Collection\",\"paymentType\":\"CASH\",\"addressDisplay\":\"1260 High Rd, London, N20 9HH\",\"contactNumber\":\"+442084467888\",\"domain\":\"www.bbqgrillwhetstone.co.uk\",\"currencyDisplay\":\"GBP\",\"commissionRate\":5,\"referenceNumber\":\"1\",\"receiptLogoUrl\":\"http://foodit-prod.appspot.com/api/v1/serveLogo/AMIfv94d6PQjKmDQU27RTz8vDRqEzeTwgsXXDKpNxfxcVJSGSI_8JFq_XHTbHV_ggLv2GFLFGXaAhekq0B2J0S9Vq3emJuJnB6KgQE2rUvaawdxjng6V5sDPg6brOktvZAsW22q23vuolFCOwsu54kIsAHDI7OpUmw/\"}";
    @Rule
    public SetupAppengine setupAppengine = new SetupAppengine();
    @Rule
    public SetupObjectify setupObjectify = new SetupObjectify(RestaurantData.class);

    private RestaurantDataServiceImpl service;

    @Before
    public void addRestaurantData(){

        ofy().save().entity(restaurantData).now();

        service = new RestaurantDataServiceImpl();
    }

    private String getOrdersJson() {
        return "[" + BQGRILL_ORDER_1 + "," +
                BBQ_GRILL_ORDER_2 + "," +
                BBQ_GRILL_ORDER_3 + "]";
    }

    @Test
    public void shouldRetrieveEmptyStringIfRestaurantDataDoesNotExist() {

        RestaurantData emptyRestData = service.getRestaurantDataByName("nonExistentRest");

        assertThat(isEmpty(emptyRestData.viewData()), equalTo(true));

    }

    @Test
    public void shouldRetrieveDataForSavedRestaurant() {
        RestaurantData retrievedRestaurantData = service.getRestaurantDataByName(BBQGRILL);

        assertThat(retrievedRestaurantData, equalTo(restaurantData));
    }

    @Test
    public void shouldRetrieveOrdersPerRestaurant(){
        List<Order> orders = service.getOrders(BBQGRILL);

        assertThat(orders.size(), equalTo(3));

        for (Order order : orders){
            assertThat(order.getStoreId(),equalTo(BBQGRILL));
        }
    }


}