const Ajv = require("ajv");
const ajv = new Ajv({ strictSchema: false });


const user_schema = {
    // "$schema": "https://json-schema.org/draft/2020-12/schema",
    "$title": "User",
    "type": "object",
    "properties": {
        "id": { "type": "integer" },
        "name": { "type": "string", "minlength": 1 }
    },
    "required": ["id", "name"]
};


const testData = {
    "id": 1,
    "name": "Balaram"
};

const validate = ajv.compile(user_schema);

const valid = validate(testData);

console.log(valid);

if (valid) {
    console.log("Valid user data");
} else {
    console.log("Invalid data", validate.errors);
}