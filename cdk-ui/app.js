if (process.env.NEW_RELIC_LICENSE_KEY) {
  const newrelic = require('newrelic');
}
process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";
const express = require('express');
const path = require('path');
const favicon = require('serve-favicon');
const logger = require('morgan');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');
// const session = require('express-session');
const cookieSession = require('cookie-session')

const index = require('./routes/index');
const serviceproc = require('./routes/serviceproc');

const passport = require('passport');
const compression = require('compression')
const app = express();
const fs = require('fs');
const { send } = require('process');

//gzip compression
app.use(compression())

app.use(logger('dev'));
app.use(cookieParser());

app.use(cookieSession({
  name: 'csagreenui',
  keys: ['signedKey'],
  path: '/procurement/serviceproc'
}))

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.static(path.join(__dirname, 'dist')));
app.use(passport.initialize());

passport.serializeUser(function (user, done) {
  console.log('Serializing user');
  done(null, user);
});

passport.deserializeUser(function (obj, done) {
  console.log('DeSerializing user');
  done(null, obj);
});

const OpenIDConnectStrategy = require('passport-ci-oidc').IDaaSOIDCStrategy;

const OIDCStrategy = new OpenIDConnectStrategy({
  discoveryURL: process.env.OAUTH_ISSUER,
  // authorizationURL: process.env.OAUTH_AUTHORIZATION_URL,
  // tokenURL: process.env.OAUTH_TOKEN_URL,
  clientID: process.env.OAUTH_CLIENT_ID,
  scope: process.env.OAUTH_SCOPE,
  response_type: process.env.OAUTH_RESPONSE_TYPE,
  clientSecret: process.env.OAUTH_CLIENT_SECRET,
  callbackURL: process.env.OAUTH_CALLBACK_URL,
  skipUserProfile: process.env.OAUTH_SKIP_PROFILE,
  // issuer: process.env.OAUTH_ISSUER,
  // addCACert: process.env.OAUTH_CERT_REQUIRED,
  // CACertPathList: [process.env.OAUTH_CACERT_PATH, process.env.OAUTH_JWKCERT_PATH] 

  skipUserProfile: true,
}, function (iss, sub, profile, jwtClaims, accessToken, refreshToken, params, done) {
  process.nextTick(function () {

    profile.id_token = params.id_token;
    profile.accessToken = accessToken;
    profile.refreshToken = refreshToken;
    done(null, profile);
  })
});

passport.use(OIDCStrategy);

app.get('/procurement/serviceproc/login', passport.authenticate('openidconnect', {}));

function ensureAuthenticated(req, res, next) {
  console.log('Checking auth status.....' + req.isAuthenticated());
  if (!req.isAuthenticated()) {
    req.session.originalUrl = req.originalUrl;
    res.redirect('/login');
  } else {
    return next();
  }
}

// login
// app.get('/procurement/serviceproc/login', passport.authenticate('openidconnect', {}));

// handle callback, if authentication succeeds redirect to
// original requested url, otherwise go to /failure
app.get('/procurement/serviceproc/callback', function (req, res, next) {
  var redirect_url = req.session.originalUrl;
 
  passport.authenticate('openidconnect', {
    successRedirect: redirect_url,
    failureRedirect: '/failure',
  })(req, res, next);
});

// load ui configuration
app.use('/procurement/serviceproc/loadConfig', loadConfig);

function loadConfig(req, res) {

  res.status(200);
  res.send({
    'GATEWAY_API': process.env.GATEWAY_API,
    'UI2FP_SECRET': getEncryptedData(process.env.UI2FP_SECRET),
    'UI2TM_SECRET': getEncryptedData(process.env.UI2TM_SECRET),
    'UI2SUBMIT_SECRET': getEncryptedData(process.env.UI2SUBMIT_SECRET),
    'ENCRYPTION_KEY': '',
    'IDLE_TIMEOUT': process.env.IDLE_TIMEOUT,
    'RENEWAL_TIMEOUT': process.env.RENEWAL_TIME_SECONDS,
    'WORKER_INTETRVAL_IN_MILLISECS': process.env.WORKER_INTETRVAL_IN_MILLISECS,
    'OAUTH_CLIENT_ID': process.env.OAUTH_CLIENT_ID,
    'OAUTH_CLIENT_SECRET': process.env.OAUTH_CLIENT_SECRET
  });
}
// encrypting the key
var CryptoJS = require("crypto-js");
// AES encription of API access token
function getEncryptedData(value) {
  const key = CryptoJS.enc.Utf8.parse(process.env.ENCRYPTION_KEY);
  const iv = CryptoJS.enc.Utf8.parse(process.env.ENCRYPTION_KEY);
  const encrypted = CryptoJS.AES.encrypt(value, key, {
    keySize: 16,
    iv: iv,
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7
  }).toString();

  return encrypted;
}
app.get('/procurement/serviceproc/logout', function (req, res) {
  req.logOut();
  // remove all session data
  req.session = null;
  req.user = null;
  //res.redirect('/login');
});
// failure page
app.get('/failure', function (req, res) {
  res.send('login failed');
});



// app.use(express.static(path.join(__dirname, '../../dist/IBMWebApp')));
//catch all route


// app.use('/ssoCallbackSuccess1', serviceproc);
app.use('*', serviceproc);
// catch 404 and forward to error handler
app.use(function (req, res, next) {
  const err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function (err, req, res) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function (err, req, res) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});

module.exports = app;
