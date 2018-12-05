var decorationsCount = 100;
var decorations = [];
var y = 400;

function setup(){
  canvas = createCanvas(windowWidth,windowHeight);
  for (var i = 0; i < decorationsCount; i++){
    var r = random(25,50);
    var x = random(0,width);
    var y = random(0,height);
    decorations[i] = new Decoration(x, y, r);
  }
  
}

function draw(){
  background(51);
  for (var i = 0; i < decorationsCount/4; i++){
    decorations[i].move();
    decorations[i].show();
    fill(255,10);
    noStroke();
    textSize(200);
    var t = text("Error ", 100, y);
  }
  stroke(0);
  strokeWeight(3);
  for (var i = decorationsCount/4; i < decorationsCount/2; i++){
    decorations[i].move();
    decorations[i].show();
    //4
    fill(255,0,0);
    stroke(0);
    strokeWeight(2);
    textSize(200);
    text("4", 600, y);
    textSize(50);
    noStroke();
    fill(252,255,100,10);
    text("Regeneration", (width/2)-150, height-50);
  }
  for (var i = decorationsCount/2; i < 3*decorationsCount/4; i++){
    decorations[i].move();
    decorations[i].show();
    fill(255,255,0);
    stroke(0);
    strokeWeight(3);
    textSize(200);
    text("0", 710, y);
    //Coding
    textSize(50);
    noStroke();
    fill(252,255,100,10);
    text("Coding", (width/2)+170, height-50);
  }
  for (var i = 3*decorationsCount/4; i < decorationsCount; i++){
    decorations[i].move();
    decorations[i].show();
    fill(255,0,0);
    stroke(0);
    strokeWeight(3);
    textSize(200);
    text("4", 825, y);
    //School
    textSize(50);
    noStroke();
    fill(252,255,100,10);
    text("School", (width/2)+340, height-50);
  }
  
}

//function mousePressed(){
//  for (var i = decorations.length-1; i >= 0; i--){
//    if (decorations[i].wasClicked(mouseX,mouseY)){
//      decorations.splice(i,1);
//    }
//  } 
//}
