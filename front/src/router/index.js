import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior() {
    return { x: 0, y: 0 }
  },
  routes: [
    {
    path: '/',
    name: 'home',
    component: () => import('@/views/Home.vue'),
    meta: {
      pageTitle: 'Home',
      breadcrumb: [
        {
          text: 'Home',
        },
        {
          text: 'Layout Without Menu',
          active: true,
        },
      ],
    },
    },
    {
      path: '/second-page',
      name: 'second-page',
      component: () => import('@/views/SecondPage.vue'),
      meta: {
        pageTitle: 'Second Page',
        breadcrumb: [
          {
            text: 'Second Page',
            active: true,
          },
        ],
      },
    },
    {
      path: '/pages/authentication/login',
      name: 'auth-login',
      component: () => import('@/views/pages/authentication/Login.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/pages/authentication/register',
      name: 'auth-register',
      component: () => import('@/views/pages/authentication/Register.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/pages/authentication/forgot-password',
      name: 'auth-forgot-password',
      component: () => import('@/views/pages/authentication/ForgotPassword.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/pages/authentication/reset-password',
      name: 'auth-reset-password',
      component: () => import('@/views/pages/authentication/ResetPassword.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/pages/authentication/create-classRoom',
      name: 'auth-create-classRoom',
      component: () => import('@/views/pages/authentication/create-classRoom.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/pages/authentication/invite-classRoom',
      name: 'auth-invite-classRoom',
      component: () => import('@/views/pages/authentication/invite-classRoom.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/error-404',
      name: 'error-404',
      component: () => import('@/views/error/Error404.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '*',
      redirect: 'error-404',
    },
  ],
})

// ? For splash screen
// Remove afterEach hook if you are not using splash screen
router.afterEach(() => {
  // Remove initial loading
  const appLoading = document.getElementById('loading-bg')
  if (appLoading) {
    appLoading.style.display = 'none'
  }
})

export default router
