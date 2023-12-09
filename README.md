<h1>About CardComponents</h1>
<h2>Making A Card UI Component</h2>
CardComp MyCp = new {CardType}();
- We use the Interface as a contract, feel free to see the differences between the two incase you need it for class conversion;
<h2>To get a Swing UI component</h2>
do - MyCp.getComponent();
<h2>To Parse A TextField's/Button</h2>
MyCp.getQuestionInput(); // Question TextField;
MyCp.getAnswerInput(); // Answer
<h3>This also supports chaining for setters</h3>
