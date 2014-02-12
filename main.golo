module hybridapp

import m33ki.spark

import app.db.Database


import app.controllers.Application
import app.controllers.PostitsController

import config

function main = |args| {

  initialize(): static("/public"): port(8888): error(true)
  listen(true) # listen to change, then compile java file

  Database("localhost", 27017, "postits")

  GET("/about", |request, response| -> Application(): about(request, response))

  POST("/postits", |request, response| -> PostitsController(): insert(request, response))

  PUT("/postits/:id", |request, response| -> PostitsController(): update(request, response))

  GET("/postits/:id", |request, response| -> PostitsController(): getOne(request, response))

  DELETE("/postits/:id", |request, response| -> PostitsController(): delete(request, response))

  GET("/postits", |request, response| -> PostitsController(): getAll(request, response))

  GET("/coucou", |request, response| -> Application(): coucou(request, response))


}