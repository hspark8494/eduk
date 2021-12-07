export default {
  // Endpoints
  //TODO : test말고 진짜 경로로 넘겨보기
  loginEndpoint: '/login',
  registerEndpoint: '/register',
  refreshEndpoint: '/jwt/refresh-token',
  logoutEndpoint: '/logout',

  // This will be prefixed in authorization header with token
  // e.g. Authorization: Bearer <token>
  tokenType: '',

  // Value of this property will be used as key to store JWT token in storage
  storageTokenKeyName: 'accessToken',
  storageRefreshTokenKeyName: 'refreshToken',
}
