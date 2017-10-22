var canvas;
var step = 0.01;
var scale = 20;
var cols, rows;

function setup() {
  cnv = createCanvas(400,400);//(windowWidth, windowHeight);
  cnv.style('z-index','-1');
  background(200);
  cols = floor(width / scale);
  rows = floor(height / scale);
}

function draw() {
    centerCanvas();
    background(255);
    var yoff=0;
    for (var y=0; y < rows; y++){
      var xoff=0;
      for (var x=0; x < cols; x++){
        var index = (x + y * width) * 4;
        var r = noise(xoff, yoff) * 255;
        var v = p5.Vector.fromAngle(0);
        xoff += step;
        stroke(0);
        push();
        translate(x * scale, y * scale);
        rotate(v.heading());
        line(0,0,scale,0);
        pop();

      }
      yoff += step;
    }
}

function centerCanvas() {
  var x = (windowWidth - width) / 2;
  var y = (windowHeight - height) / 2;
  cnv.position(x, y);
}

function windowResized() {
    centerCanvas();
}