var marked = require('marked');
var fs = require('fs');

var readMe = fs.readFileSync('/IPASS/DESIGN/PlanVanAanpak.md', 'utf-8');
var markdownReadMe = marked(readMe);

fs.writeFileSync('/IPASS/DESIGN/index.html', markdownReadMe);