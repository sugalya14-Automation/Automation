import {test, expect} from '@playwright/test';

test('Google Search Test',async({page}) => {
await page.goto('https://www.wikipedia.org');
await page.locator('//*[@id="searchInput"]').fill('netflix');
await page.keyboard.press('Enter');
await page.waitForTimeout(5000);
await page.waitForLoadState('domcontentloaded');
const results = await page.locator('h3').allTextContents();
expect(results.length).toBeGreaterThan(0);
});