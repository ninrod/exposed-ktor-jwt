const db_filename = 'json-server.db.json';
const fs = require('fs');
const jsonServer = require('json-server');
const server = jsonServer.create();
const router = jsonServer.router(db_filename);
const middlewares = jsonServer.defaults();

// Set default middlewares (logger, static, cors and no-cache)
server.use(middlewares);

// handle genérico para POSTS, PUTS, PATCHES, etc...
server.use(jsonServer.bodyParser);
server.use((req, res, next) => {
  if (req.method === 'POST') {
    req.body.my_timestamp = Date.now();
  }
  next(); // Continue to JSON Server router
});

// Add custom routes before JSON Server router
server.get('/my/custom/path', (req, res) => {
  fs.readFile(__dirname + '/' + db_filename, 'utf8', (err, data) => {
    if (err) throw err;
    var obj = JSON.parse(data).booleans;
    var payload = { "bool1": obj.bool1, "feature2": obj.bool2 };
    payload.vector = JSON.parse(data).vector;
    res.jsonp(payload);
  });
});

// um exemplo genérico de post
server.post('/try', (req, res) => {
  req.body.mensagem = 'yes!';
  res.jsonp(req.body);
});

// route redirection
server.use(jsonServer.rewriter({
  "/diagnostico/version": "/diagnostic",
  "/login\\?u=:user&p=:pass": "/user",
}));

// Use default router
server.use(router);
server.listen(3000, () => {
  console.log('MY CUSTOM JSON Server is running');
});
