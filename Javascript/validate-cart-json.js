const Ajv = require("ajv");
const ajv = new Ajv({ strictSchema: false });

const cart_schema = {
    "$title": "Cart",
    "type": "object",
    "properties": {
        "userId": { "type": "integer" },
        "items": {
            "type": "array",
            "items": {
                "type": "object",
                "properties": {
                    "productId": { "type": "integer" },
                    "quantity": { "type": "integer", "minimum": 1 }
                },
                "required": ["productId", "quantity"]
            }
        }
    },
    "required": ["userId", "items"]
};

const testData = {
    "userId": 1,
    "items": [
        { "productId": 101, "quantity": 2 },
        { "productId": 103, "quantity": 1 }
    ]
};

const validate = ajv.compile(cart_schema);
const valid = validate(testData);

console.log(valid);

if (valid) {
    console.log("Valid cart data");
} else {
    console.log("Invalid data", validate.errors);
}
