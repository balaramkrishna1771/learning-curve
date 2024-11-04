const Ajv = require("ajv");
const ajv = new Ajv({ strictSchema: false });

const product_schema = {
    "$title": "Product",
    "type": "object",
    "properties": {
        "productId": { "type": "integer" },
        "productName": { "type": "string", "minLength": 1 },
        "price": { "type": "number", "minimum": 0 },
        "tags": { "type": "array", "items": { "type": "string" } }
    },
    "required": ["productId", "productName", "price"]
};

const testData = {
    "productId": 101,
    "productName": "Laptop",
    "price": 999.99,
    "tags": ["electronics", "computer"]
};

const validate = ajv.compile(product_schema);
const valid = validate(testData);

console.log(valid);

if (valid) {
    console.log("Valid product data");
} else {
    console.log("Invalid data", validate.errors);
}
