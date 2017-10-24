let canvas;
let target
let timeWindow;
function setup(){
    canvas = createCanvas(windowWidth,windowHeight);
    canvas.style('z-index','-25');
    background(51);
    generateEnemy();
}

function draw(){

}

function mousePressed(){
    if ((mouseX==target.position.x-12.5 || mouseX==target.position.y+12.5)
        && (mouseY==target.position.y-12.5 || mouseY==target.position.y+12.5)){
            generateEnemy();
        }
}

function generateEnemy(){
    background(51);
    fill(255,0,0);
    stroke(0);
    target = ellipse(random(0,windowWidth),random(0,windowHeight),25,25);
}