import { JwtSampleAppPage } from './app.po';

describe('jwt-sample-app App', () => {
  let page: JwtSampleAppPage;

  beforeEach(() => {
    page = new JwtSampleAppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to jwtapp!!');
  });
});
