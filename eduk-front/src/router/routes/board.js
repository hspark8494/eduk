export default [
  {
    path: '/:boardId/postWrite',
    name: 'post-write',
    component: () => import('@/views/board/postWrite.vue'),
    meta: {
      pageTitle: '게시판 글 작성',
      breadcrumb: [
        {
          text: '게시판',
        },
        {
          text: '글 작성',
          active: true,
        },
      ],
    },
  },
  {
    path: '/:boardId/postList/:nowPage',
    name: 'post-list',
    component: () => import('@/views/board/postList.vue'),
    meta: {
      pageTitle: '게시글 목록',
      breadcrumb: [
        {
          text: '게시판',
        },
        {
          text: '글 목록',
          active: true,
          props: true,
        },
      ],
    },
  },
  {
    path: '/:boardId/postRead/:postId',
    name: 'post-read',
    component: () => import('@/views/board/postRead.vue'),
    meta: {
      pageTitle: '게시글 상세보기',
      breadcrumb: [
        {
          text: '게시판',
        },
        {
          text: '상세보기',
          active: true,
        },
      ],
    },
  },
  {
    path: '/:boardId/postUpdate/:postId',
    name: 'post-update',
    component: () => import('@/views/board/postUpdate.vue'),
    meta: {
      pageTitle: '게시판 글 수정',
      breadcrumb: [
        {
          text: '게시판',
        },
        {
          text: '글 수정',
          active: true,
        },
      ],
    },
  },
  
]
