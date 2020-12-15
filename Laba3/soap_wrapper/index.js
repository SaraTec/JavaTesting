const express = require("express");
const app = express();
const soap = require("soap");
let chai = require("chai");
let chaiHttp = require("chai-http");
app.set("port", process.env.PORT || 5000);
chai.use(chaiHttp);
const server = app.listen(app.get("port"), () => {
  console.log(`Server started on ${app.get("port")}`);
});

app.post("/task1", (req, res) => {
  soap.createClientAsync("./TextCasing.wsdl").then(client => {
    client.TitleCaseWordsWithToken(
      { sText: req.query.string, sToken: req.query.token },
      (err, result) => {
        res.json({ result: result.TitleCaseWordsWithTokenResult });
      }
    );
  });
});
app.post("/task2", (req, res) => {
  soap.createClientAsync("./TextCasing.wsdl").then(client => {
    client.InvertStringCase({ sAString: req.query.string }, (err, result) => {
      res.json({ result: result.InvertStringCaseResult });
    });
  });
});
app.post("/task3", (req, res) => {
  soap.createClientAsync("./TextCasing.wsdl").then(client => {
    client.InvertCaseFirstAdjustStringToPrevious(
      { sAString: req.query.string },
      (err, result) => {
        res.json({
          result: result.InvertCaseFirstAdjustStringToPreviousResult
        });
      }
    );
  });
});
app.post("/task4", (req, res) => {
  soap.createClientAsync("./TextCasing.wsdl").then(client => {
    client.InvertCaseFirstAdjustStringToCurrent(
      { sAString: req.query.string },
      (err, result) => {
        res.json({
          result: result.InvertCaseFirstAdjustStringToCurrentResult
        });
      }
    );
  });
});
app.post("/task5", (req, res) => {
  soap.createClientAsync("./TextCasing.wsdl").then(client => {
    client.AllUppercaseWithToken(
      { sAString: req.query.string, sToken: req.query.token },
      (err, result) => {
        res.json({ result: result.AllUppercaseWithTokenResult });
      }
    );
  });
});
app.post("/task6", (req, res) => {
  soap.createClientAsync("./TextCasing.wsdl").then(client => {
    client.AllLowercaseWithToken(
      { sAString: req.query.string, sToken: req.query.token },
      (err, result) => {
        res.json({ result: result.AllLowercaseWithTokenResult });
      }
    );
  });
});
app.post("/task7", (req, res) => {
  soap.createClientAsync("./TextCasing.wsdl").then(client => {
    client.UppercaseWordsWithToken(
      { sAString: req.query.string, sToken: req.query.token },
      (err, result) => {
        res.json({ result: result.UppercaseWordsWithTokenResult });
      }
    );
  });
});
app.post("/task8", (req, res) => {
  soap.createClientAsync("./TextCasing.wsdl").then(client => {
    client.LowercaseWordsWithToken(
      { sAString: req.query.string, sToken: req.query.token },
      (err, result) => {
        res.json({ result: result.LowercaseWordsWithTokenResult });
      }
    );
  });
});

module.exports = server;
