/**
 *  Copyright 2014 Reverb Technologies, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.wordnik.swagger.sample.resource;

import com.wordnik.swagger.sample.data.StoreData;
import com.wordnik.swagger.sample.model.Order;
import com.wordnik.swagger.sample.exception.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/store")
@Produces({"application/json", "application/xml"})
public class PetStoreResource {
  static StoreData storeData = new StoreData();
  static JavaRestResourceUtil ru = new JavaRestResourceUtil();

  @GET
  @Path("/order/{orderId}")
  public Response getOrderById(@PathParam("orderId") String orderId)
      throws NotFoundException {
    Order order = storeData.findOrderById(ru.getLong(0, 10000, 0, orderId));
    if (null != order) {
      return Response.ok().entity(order).build();
    } else {
      throw new NotFoundException(404, "Order not found");
    }
  }

  @POST
  @Path("/order")
  public Response placeOrder(Order order) {
    storeData.placeOrder(order);
    return Response.ok().entity("").build();
  }

  @DELETE
  @Path("/order/{orderId}")
  public Response deleteOrder(@PathParam("orderId") String orderId) {
    storeData.deleteOrder(ru.getLong(0, 10000, 0, orderId));
    return Response.ok().entity("").build();
  }
}
