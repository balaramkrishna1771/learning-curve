const Ajv = require("ajv");
const ajv = new Ajv({ strictSchema: false });

const review_schema = {
    "$title": "Review",
    "type": "object",
    "properties": {
        "reviewId": { "type": "integer" },
        "productId": { "type": "integer" },
        "userId": { "type": "integer" },
        "rating": { "type": "integer", "minimum": 1, "maximum": 5 },
        "comment": { "type": "string", "maxLength": 500 }
    },
    "required": ["reviewId", "productId", "userId", "rating"]
};

const testData = {
    "reviewId": 301,
    "productId": 101,
    "userId": 1,
    "rating": 5,
    "comment": "Excellent product!"
};

const validate = ajv.compile(review_schema);
const valid = validate(testData);

console.log(valid);

if (valid) {
    console.log("Valid review data");
} else {
    console.log("Invalid data", validate.errors);
}
