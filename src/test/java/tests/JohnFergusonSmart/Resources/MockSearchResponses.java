package tests.JohnFergusonSmart.Resources;

public class MockSearchResponses {

    public static final String RESPONSE_WITH_NO_ENTRIES = """
            {
                "current_page": 1,
                "data": [],
                "from": 1,
                "last_page": 1,
                "per_page": 9,
                "to": 1,
                "total": 0
            }
            """;

    public static final String RESPONSE_WITH_A_SINGLE_ENTRY = """
            {
                "current_page": 1,
                "data": [
                    {
                        "id": "01K978CJ94FNNKF6614PRJ5XA2",
                        "name": "Testing Pliers",
                        "description": "Testing Pliers for all your testing needs.",
                        "price": 14.15,
                        "is_location_offer": false,
                        "is_rental": false,
                        "co2_rating": "D",
                        "in_stock": true,
                        "is_eco_friendly": false,
                        "product_image": {
                            "id": "01K978CJ8F9H1R9S53TGXFJD5D",
                            "by_name": "Helinton Fantin",
                            "by_url": "https:\\/\\/unsplash.com\\/@fantin",
                            "source_name": "Unsplash",
                            "source_url": "https:\\/\\/unsplash.com\\/photos\\/W8BNwvOvW4M",
                            "file_name": "pliers01.avif",
                            "title": "Combination pliers"
                        },
                        "category": {
                            "id": "01K978CJ81PM5GWN2D1S3JHGJW",
                            "name": "Pliers"
                        },
                        "brand": {
                            "id": "01K978CHVQVFRWCGKC0VT8Y694",
                            "name": "ForgeFlex Tools"
                        }
                    }
                ],
                "from": 1,
                "last_page": 1,
                "per_page": 9,
                "to": 4,
                "total": 4
            }
            """;


}
