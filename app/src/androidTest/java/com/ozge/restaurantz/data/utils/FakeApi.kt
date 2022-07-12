package com.ozge.restaurantz.utils

import com.ozge.restaurantz.data.entity.RestaurantEntity
import com.ozge.restaurantz.domain.model.RestaurantUIModel

val restaurant1 = RestaurantEntity(
    5818,
    "5d4276bc-bab8-43b9-b50a-ad1eb1e1e8fd",
    "Golden Pub",
    "Senegalese",
    "Culver’s Restaurant was founded by the Culver family in 1984, which eventually branched out to more than 300 franchised restaurants all over the US. Culver’s is well-known for its ButterBurger, which made the restaurant extremely famous. They also have other items which include salads, sandwiches, desserts, etc.",
    "Brand new. Great design. Odd to hear pop music in a Mexican establishment. Music is a bit loud. It should be background.",
    "https://loremflickr.com/500/500/restaurant",
    "(523) 116-1089 x43950",
    "Suite 107 6496 Runte Stream, Port Christianfurt, NE 38014"
)

val restaurant2 = RestaurantEntity(
    2155,
    "9dabc2cc-483e-4e4d-895b-6ff2e7f8f142",
    "Salty Eats",
    "Bakery",
    "To deliver an exceptional shopping experience by offering the best service, value, quality, and freshest products while being good stewards of our environment and giving back to the communities we serve.",
    "For dinner we ordered the shrimp enchiladas, chicken enchiladas, chicken burrito, chimichangas, and steak quesadillas. Everything was so tasty and amazing. I wasnt surprised because the food at the FiDi location is the best so I figured this location would be just as good and it was!!! The enchiladas with the green sauce is to die for. My go to at the FiDi location is usually the chicken enchiladas but I decided to try something new and the shrimp enchiladas did not disappoint.",
    "https://loremflickr.com/500/500/restaurant",
    "977.096.5146 x2252",
    "Apt. 112 52324 Karina Divide, Lake Ellsworth, UT 68962-7915",

    )

val restaurantList = listOf(restaurant1, restaurant2)

val restaurantUIModel1 = RestaurantUIModel(
    5818,
    "5d4276bc-bab8-43b9-b50a-ad1eb1e1e8fd",
    "Golden Pub",
    "Senegalese",
    "Culver’s Restaurant was founded by the Culver family in 1984, which eventually branched out to more than 300 franchised restaurants all over the US. Culver’s is well-known for its ButterBurger, which made the restaurant extremely famous. They also have other items which include salads, sandwiches, desserts, etc.",
    "Brand new. Great design. Odd to hear pop music in a Mexican establishment. Music is a bit loud. It should be background.",
    "https://loremflickr.com/500/500/restaurant",
    "(523) 116-1089 x43950",
    "Suite 107 6496 Runte Stream, Port Christianfurt, NE 38014"
)

val restaurantUIModel2 = RestaurantUIModel(
    2155,
    "9dabc2cc-483e-4e4d-895b-6ff2e7f8f142",
    "Salty Eats",
    "Bakery",
    "To deliver an exceptional shopping experience by offering the best service, value, quality, and freshest products while being good stewards of our environment and giving back to the communities we serve.",
    "For dinner we ordered the shrimp enchiladas, chicken enchiladas, chicken burrito, chimichangas, and steak quesadillas. Everything was so tasty and amazing. I wasnt surprised because the food at the FiDi location is the best so I figured this location would be just as good and it was!!! The enchiladas with the green sauce is to die for. My go to at the FiDi location is usually the chicken enchiladas but I decided to try something new and the shrimp enchiladas did not disappoint.",
    "https://loremflickr.com/500/500/restaurant",
    "977.096.5146 x2252",
    "Apt. 112 52324 Karina Divide, Lake Ellsworth, UT 68962-7915",
)

val restaurantUIList = listOf(restaurantUIModel1, restaurantUIModel2)