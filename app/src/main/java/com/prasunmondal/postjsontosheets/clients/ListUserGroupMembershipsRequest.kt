//package com.prasunmondal.postjsontosheets.clients
//
//import java.util.function.Consumer
//
//class ListUserGroupMembershipsRequest {
//
//    constructor(compartmentId: String?,
//                userId: String?,
//                groupId: String?,
//                page: String?,
//                limit: Int?) : BmcRequest()
//{
//
//    class Builder internal constructor() {
//        private var compartmentId: String? = null
//        private var userId: String? = null
//        private var groupId: String? = null
//        private var page: String? = null
//        private var limit: Int? = null
//        private var invocationCallback: Consumer<Builder>? = null
//        fun invocationCallback(invocationCallback: Consumer<Builder?>): Builder {
//            this.invocationCallback = invocationCallback
//            return this
//        }
//
//        fun build(): ListUserGroupMembershipsRequest {
//            val request = buildWithoutInvocationCallback()
//            request.setInvocationCallback(invocationCallback)
//            return request
//        }
//
//        fun compartmentId(compartmentId: String?): Builder {
//            this.compartmentId = compartmentId
//            return this
//        }
//
//        fun userId(userId: String?): Builder {
//            this.userId = userId
//            return this
//        }
//
//        fun groupId(groupId: String?): Builder {
//            this.groupId = groupId
//            return this
//        }
//
//        fun page(page: String?): Builder {
//            this.page = page
//            return this
//        }
//
//        fun limit(limit: Int?): Builder {
//            this.limit = limit
//            return this
//        }
//
//        fun buildWithoutInvocationCallback(): ListUserGroupMembershipsRequest {
//            return ListUserGroupMembershipsRequest(compartmentId, userId, groupId, page, limit)
//        }
//
//        override fun toString(): String {
//            return "ListUserGroupMembershipsRequest.Builder(compartmentId=" + compartmentId + ", userId=" + userId + ", groupId=" + groupId + ", page=" + page + ", limit=" + limit + ")"
//        }
//    }
//
//    companion object {
//        fun builder(): Builder {
//            return Builder()
//        }
//    }
//}