let chai = require("chai");
let server = require("../index.js");
var expect = chai.expect;
describe("SOAP Testing", () => {
  it("Task 1", done => {
    chai
      .request(server)
      .post("/task1")
      .query({ string: "hello world", token: "1" })
      .end((err, res) => {
        console.log(
          "1. Make first character uppercase adding token between word"
        );

        expect(res.body.result).to.be.eql("Hello1World");
        done();
      });
  });
  it("Task 2", done => {
    chai
      .request(server)
      .post("/task2")
      .query({ string: "BohDan TeReNchyn" })
      .end((err, res) => {
        console.log(
          "2. Uppercase letters that are lowercase and lowercase letters that are uppercase"
        );
        expect(res.body.result).to.be.eql("bOHdAN tErEnCHYN");
        done();
      });
  });
  it("Task 3", done => {
    chai
      .request(server)
      .post("/task3")
      .query({ string: "Bohdan teRenchyn" })
      .end((err, res) => {
        console.log(
          "3. Invert the casing of the first letter and adjust the string to conform with the previous casing of first letter"
        );
        expect(res.body.result).to.be.eql("bOHDAN Terenchyn");
        done();
      });
  });
  it("Task 4", done => {
    chai
      .request(server)
      .post("/task4")
      .query({ string: "Bohdan teRenchyn" })
      .end((err, res) => {
        console.log(
          "4. Invert the casing of the first letter and adjust the string to conform with the current casing of first letter"
        );
        expect(res.body.result).to.be.eql("bohdan TERENCHYN");
        done();
      });
  });
  it("Task 5", done => {
    chai
      .request(server)
      .post("/task5")
      .query({ string: "teRenchyn", token: "1" })
      .end((err, res) => {
        console.log(
          "5. Change string to uppercase adding token between characters"
        );
        expect(res.body.result).to.be.eql("1T1E1R1E1N1C1H1Y1N1");
        done();
      });
  });
  it("Task 6", done => {
    chai
      .request(server)
      .post("/task6")
      .query({ string: "BohDan TeReNchyn", token: "123" })
      .end((err, res) => {
        console.log(
          "6. Change string to lowercase adding token between characters"
        );
        expect(res.body.result).to.be.eql(
          "123b123o123h123d123a123n123 123t123e123r123e123n123c123h123y123n123"
        );
        done();
      });
  });
  it("Task 7", done => {
    chai
      .request(server)
      .post("/task7")
      .query({ string: "BohDan TeReNchyn", token: "000" })
      .end((err, res) => {
        console.log(
          "7. Change string to uppercase adding token between characters of each word"
        );
        expect(res.body.result).to.be.eql("BOHDAN 000TERENCHYN");
        done();
      });
  });
  it("Task 8", done => {
    chai
      .request(server)
      .post("/task8")
      .query({ string: "BohDan TeReNchyn", token: "555" })
      .end((err, res) => {
        console.log(
          "8.  Change string to lowercase adding token between characters of each word"
        );
        expect(res.body.result).to.be.eql("bohdan 555terenchyn");
        done();
      });
  });
});
