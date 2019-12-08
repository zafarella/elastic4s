package com.sksamuel.elastic4s.requests.get

import com.sksamuel.elastic4s.HttpEntity.StringEntity
import com.sksamuel.elastic4s.{ElasticError, HttpResponse}
import org.scalatest.EitherValues
import matchers.should.Matchers._
import org.scalatest.matchers
import org.scalatest.flatspec.AnyFlatSpec

class GetHandlerTest extends AnyFlatSpec with GetHandlers with EitherValues {

  it should "parse proxy errors correctly" in {

    val responseBody = """{"ok":false,"message":"Deployment is under maintenance / recovery."}"""
    val response = HttpResponse(503, Some(StringEntity(responseBody, None)), Map.empty)

    // not sure we want the complete body in both type and reason
    GetHandler.responseHandler.handle(response).left.value shouldBe ElasticError(responseBody, responseBody, None, None, None, Seq.empty, None)
  }

}
