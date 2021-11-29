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
      path: '/login',
      name: 'login',
      component: () => import('@/views/Login.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/login',
      name: 'auth-login',
      component: () => import('@/views/pages/authentication/Login.vue'),
      meta: {
        layout: 'full',
        resource: 'Auth',
        redirectIfLoggedIn: true,
      },
    },
    {
      path: '/pages/authentication/login-v1',
      name: 'auth-login-v1',
      component: () => import('@/views/pages/authentication/Login-v1.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/pages/authentication/login-v2',
      name: 'auth-login-v2',
      component: () => import('@/views/pages/authentication/Login-v2.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/register',
      name: 'auth-register',
      component: () => import('@/views/pages/authentication/Register.vue'),
      meta: {
        layout: 'full',
        resource: 'Auth',
        redirectIfLoggedIn: true,
      },
    },
    {
      path: '/pages/authentication/register-v1',
      name: 'auth-register-v1',
      component: () => import('@/views/pages/authentication/Register-v1.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/pages/authentication/register-v2',
      name: 'auth-register-v2',
      component: () => import('@/views/pages/authentication/Register-v2.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/forgot-password',
      name: 'auth-forgot-password',
      component: () => import('@/views/pages/authentication/ForgotPassword.vue'),
      meta: {
        layout: 'full',
        resource: 'Auth',
        redirectIfLoggedIn: true,
      },
    },
    {
      path: '/pages/authentication/forgot-password-v1',
      name: 'auth-forgot-password-v1',
      component: () => import('@/views/pages/authentication/ForgotPassword-v1.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/pages/authentication/forgot-password-v2',
      name: 'auth-forgot-password-v2',
      component: () => import('@/views/pages/authentication/ForgotPassword-v2.vue'),
      meta: {
        layout: 'full',
        resource: 'Auth',
      },
    },
    {
      path: '/pages/authentication/reset-password-v1',
      name: 'auth-reset-password-v1',
      component: () => import('@/views/pages/authentication/ResetPassword-v1.vue'),
      meta: {
        layout: 'full',
      },
    },
    {
      path: '/pages/authentication/reset-password-v2',
      name: 'auth-reset-password-v2',
      component: () => import('@/views/pages/authentication/ResetPassword-v2.vue'),
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
