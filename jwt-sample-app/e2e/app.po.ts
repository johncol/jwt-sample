import { browser, by, element } from 'protractor';

export class JwtSampleAppPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('jwtapp-root h1')).getText();
  }
}
