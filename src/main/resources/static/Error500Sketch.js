var textBubbleA;
var textBubbleB;
var textBubbleC;
var textBubbles = [];
var errorText;
var itsOkText;
var justClickText;

function setup(){
  createCanvas(windowWidth,windowHeight);
  
  textBubbleA = new TextBubble(windowWidth/4,windowHeight/2,'5',random(200));
  textBubbles.push(textBubbleA);
  textBubbleB = new TextBubble(windowWidth/2,windowHeight/2,'0',random(200));
  textBubbles.push(textBubbleB);
  textBubbleC = new TextBubble(3*windowWidth/4,windowHeight/2,'0',random(200)); 
  textBubbles.push(textBubbleC);
  
  text
  errorText = new TextRectangle(windowWidth/2,windowHeight/4,'Error');
  itsOkText = new TextRectangle(windowWidth/2,windowHeight/2,'But it\'s ok!');
  justClickText = new TextRectangle(windowWidth/2,3*windowHeight/4,'Just click on the numbers!');
  
  
}


function draw(){
  background(51);
  if (textBubbles.length == 0){
    noLoop();
    window.location = "http://localhost:8080";
  }
  for (var i = textBubbles.length-1; i >= 0; i--){
    textBubbles[i].move();
    textBubbles[i].show();
  }
  errorText.move();
  errorText.show();
  itsOkText.move();
  itsOkText.show();
  justClickText.move();
  justClickText.show();
  
}

function redirect(){
  window.location = "http://www.google.com";
}


function mousePressed(){
  for (var i = textBubbles.length-1; i >= 0; i--){
    if (textBubbles[i].wasClicked(mouseX,mouseY)){
      textBubbles.splice(i,1);
    }
  } 
}