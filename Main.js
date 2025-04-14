// Your code here
const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let inputs = [];

rl.on("line", (line) => {
    inputs.push(line);

    if (inputs.length === 2) {
        const string1 = inputs[0];
        const string2 = inputs[1];

        function isAnagram(str1, str2) {
            str1 = str1.replace(/\s/g, "").toLowerCase();
            str2 = str2.replace(/\s/g, "").toLowerCase();

            if (str1.length !== str2.length) {
                return false;
            }

            return [...str1].sort().join("") === [...str2].sort().join("");
        }

        console.log(isAnagram(string1, string2) ? "true" : "false");
        rl.close();
    }
});
