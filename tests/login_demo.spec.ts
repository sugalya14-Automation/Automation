import {test, expect, Locator} from '@playwright/test';
import {takeScreenshot,createWordReport} from './CommonFunctions';

test('Login Test',async({page},testInfo) => {
await page.goto('https://saucedemo.com/');
const username: Locator = page.locator('#user-name');
const password: Locator = page.locator('#password');
const LoginButon: Locator = page.locator('#login-button');
const filename = 'screenshots/Home-'+Date.now()+'.png';
await takeScreenshot(page);
await username.fill('standard_user');
await password.fill('secret_sauce');
await takeScreenshot(page);
await LoginButon.click();

await page.waitForLoadState('domcontentloaded');
await expect(page).toHaveURL('https://www.saucedemo.com/inventory.html');
const title: Locator = page.locator('.title');
await takeScreenshot(page);

await createWordReport(testInfo);
});
