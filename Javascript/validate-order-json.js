const Ajv = require("ajv");
const ajv = new Ajv({ strictSchema: false });

const order_schema = {
    "$title": "Order",
    "type": "object",
    "properties": {
        "orderId": { "type": "integer" },
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
    "required": ["orderId", "userId", "items"]
};

const testData = {
    "orderId": 201,
    "userId": 1,
    "items": [
        { "productId": 101, "quantity": 2 },
        { "productId": 102, "quantity": 1 }
    ]
};

const validate = ajv.compile(order_schema);
const valid = validate(testData);

console.log(valid);

if (valid) {
    console.log("Valid order data");
} else {
    console.log("Invalid data", validate.errors);
}
